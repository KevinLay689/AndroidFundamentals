package com.example.kevinlay.androidfundamentalspractice.DataPersistence;

import android.Manifest;
import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kevinlay.androidfundamentalspractice.R;

/**
 * Content Provider Activity
 *
 * Content Providers work like this:
 * -ContentResolver communicates with the other apps ContentProvider. ContentProvider receivers data requests
 * from client, then performs the action and returns the result.
 * -Use a CursorLoader to run asynchronous queries in background
 * -The Activity or Fragment in your UI call a CursorLoader to the query, which in turn gets the ContentProvider using the ContentResolver.
 *
 * Example Query
         mCursor = getContentResolver().query(
         UserDictionary.Words.CONTENT_URI,   // The content URI of the words table // FROM
         mProjection,                        // The columns to return for each row // Array
         mSelectionClause                    // Selection criteria // WHERE // Array
         mSelectionArgs,                     // Selection criteria // For adding a ? to SelectionClause and replacing the ?
         mSortOrder);                        // The sort order for the returned rows // ORDER BY
 *
 *
 * Steps to Retrieving a Content Provider
 *      1. Get the Contact Resolver using getContentResolver
 *      2. Create a Cursor using the Content Resolver
 *          2a. Cursor cursor = contentResolver.query(URI OR FROM, COLUMNS TO RETURN, WHERE CLAUSE, SELECTIONARGS, ORDER BY)
 *      3. Iterate through the cursor to get the data
 *
 * Steps to use a CursorLoader to run asynchronous queries in background
 *      1. Implement the LoaderManager.LoaderCallbacks interface, Once loader is initialized it will execute callback methods
 *          1a. Override onCreateLoader - Pass content provider URI - Worker thread
 *          1b. Override onLoadFinished - worker thread
 *          1c. Override onLoaderReset
 *      2. Initialize the LoaderManager using getLoaderManager().init(LoaderID, Bundle data, LoaderManager Callback Interface)
 *      3. Inside onCreateLoader - Check the i parameter if it is the same as the LoaderID, if so,
 *      return a new CursorLoader(Context, Same as Cursor)
 *      4. When it is done, onLoadFinished will execute and the cursor parameter will need to be iterated through
 *      to retrieve its information
 *
 * Tips for Content Provider
 *      1. Querying happens on the Main Thread
 *
 *
 */

public class ContentProviderActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String TAG = "ContentProviderActivity";

    private static final int READ_CONTACTS_PERMISSIONS_REQUEST = 1;

    private Button bRetrieveContacts, bCreateContentProvider;
    private String[] mColumnProjection = new String[] {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            ContactsContract.Contacts.CONTACT_STATUS};
    private String mSelectionClause = ContactsContract.Contacts.DISPLAY_NAME + " = 'Kevin'";
    private String[] mSelectionArguments = new String[] {"Kevin"};
    private String mOrderBy = ContactsContract.Contacts.DISPLAY_NAME;

    private boolean isFirstTimeLoading = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        bRetrieveContacts = (Button) findViewById(R.id.bRetrieveContacts);
        bRetrieveContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //requestContactPermissionAndRetrieveContacts();
                startContentProviderAsync();
            }
        });

        bCreateContentProvider = (Button) findViewById(R.id.bCreateContentProvider);
        bCreateContentProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void retrieveContacts() {
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                mColumnProjection,
                mSelectionClause,
                null,
                null);

        String content = "";

        if(cursor.getCount() > 0 && cursor != null) {
            while (cursor.moveToNext()) {
                content += cursor.getString(0);
                content += cursor.getString(1);
                content += cursor.getString(2);
            }
            Log.i(TAG, "onCreate: " + content);
        } else {
            Log.i(TAG, "onCreate: No Contacts");
        }
    }

    private void requestContactPermissionAndRetrieveContacts() {

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    READ_CONTACTS_PERMISSIONS_REQUEST);
        } else {
            retrieveContacts();
        }
    }

    private void startContentProviderAsync() {
        if(isFirstTimeLoading) {
            //Activity can contain multiple loaders
            int loaderId = 0;
            getLoaderManager().initLoader(loaderId, null, this);
            isFirstTimeLoading = false;
        } else {
            getLoaderManager().restartLoader(0, null, this);
        }
    }

    /**
     * The following 3 methods must be overriden while implmeneting LoadingManager.LoaderCallbacks
     */

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if(i == 0) {
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI, mColumnProjection, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        String content = "";

        if(cursor.getCount() > 0 && cursor != null) {
            while (cursor.moveToNext()) {
                content += cursor.getString(0);
                content += cursor.getString(1);
                content += cursor.getString(2);
            }
            Log.i(TAG, "onCreate: " + content);
        } else {
            Log.i(TAG, "onCreate: No Contacts");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case READ_CONTACTS_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Contact Permission Granted", Toast.LENGTH_LONG).show();
                    retrieveContacts();
                } else {
                    Toast.makeText(this, "Contact Permission Denied", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }

    }
}
