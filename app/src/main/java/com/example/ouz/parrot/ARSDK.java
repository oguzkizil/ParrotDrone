package com.example.ouz.parrot;

/**
 * Created by OĞUZ on 26.2.2018.
 */
import android.util.Log;

import com.parrot.arsdk.arsal.ARSALPrint;

public class ARSDK
{
    private static final String TAG = "ARSDK";

    public static boolean loadSDKLibs()
    {
        boolean success = false;
        try
        {
            System.loadLibrary("curl");
            System.loadLibrary("json-c");

            System.loadLibrary("arsal");
            System.loadLibrary("arsal_android");

            System.loadLibrary("arnetworkal");
            System.loadLibrary("arnetworkal_android");

            System.loadLibrary("arnetwork");
            System.loadLibrary("arnetwork_android");

            System.loadLibrary("arcommands");
            System.loadLibrary("arcommands_android");

            System.loadLibrary("pomp");
            System.loadLibrary("mux");
            System.loadLibrary("mux_android");

            System.loadLibrary("arstream");
            System.loadLibrary("arstream_android");

            System.loadLibrary("arstream2");
            System.loadLibrary("arstream2_android");

            System.loadLibrary("ardiscovery");
            System.loadLibrary("ardiscovery_android");

            System.loadLibrary("arutils");
            System.loadLibrary("arutils_android");

            System.loadLibrary("ardatatransfer");
            System.loadLibrary("ardatatransfer_android");

            System.loadLibrary("armedia");
            System.loadLibrary("armedia_android");

            System.loadLibrary("tar");
            System.loadLibrary("puf");

            System.loadLibrary("arupdater");
            System.loadLibrary("arupdater_android");

            System.loadLibrary("armavlink");
            System.loadLibrary("armavlink_android");

            System.loadLibrary("arcontroller");
            System.loadLibrary("arcontroller_android");

            ARSALPrint.d(TAG, "ARSDK libraries loaded");

            success = true;
        }
        catch (Exception e)
        {
            Log.e(TAG, "Oops (LoadLibrary)", e);
        }

        return success;
    }
}