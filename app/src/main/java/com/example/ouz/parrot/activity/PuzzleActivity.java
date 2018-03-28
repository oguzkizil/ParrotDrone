package com.example.ouz.parrot.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.ouz.parrot.R;
import com.example.ouz.parrot.ViewPagerAdapter;

import com.example.ouz.parrot.drone.MiniDrone;

import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_STATE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_ANIMATION_TYPE_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM;
import com.parrot.arsdk.arcommands.ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM;
import com.parrot.arsdk.arcontroller.ARCONTROLLER_DEVICE_STATE_ENUM;

import com.parrot.arsdk.arcontroller.ARControllerCodec;
import com.parrot.arsdk.arcontroller.ARFrame;
import com.parrot.arsdk.ardiscovery.ARDiscoveryDeviceService;


public class PuzzleActivity extends AppCompatActivity {

    ViewPager vwPager1;
    ViewPager vwPager2;
    ViewPager vwPager3;
    Button btnBaslat;
    Button btnAcil;
    TextView txtBatarya;
    Thread thread;

    private MiniDrone mMiniDrone;

    private ProgressDialog mConnectionProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        vwPager1 = (ViewPager) findViewById(R.id.vwPager1);
        vwPager2 = (ViewPager) findViewById(R.id.vwPager2);
        vwPager3 = (ViewPager) findViewById(R.id.vwPager3);

        btnBaslat = (Button) findViewById(R.id.btnBaslat);
        btnAcil = (Button) findViewById(R.id.btnAcil);
        txtBatarya = (TextView) findViewById(R.id.txtBatarya);

        Intent intent = getIntent();
        ARDiscoveryDeviceService service = intent.getParcelableExtra(DeviceListActivity.EXTRA_DEVICE_SERVICE);
        mMiniDrone = new MiniDrone(this, service);
        mMiniDrone.addListener(mMiniDroneListener);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        vwPager1.setAdapter(viewPagerAdapter);
        vwPager2.setAdapter(viewPagerAdapter);
        vwPager3.setAdapter(viewPagerAdapter);

        PageListener pageListener = new PageListener();

        vwPager1.addOnPageChangeListener(pageListener);
        vwPager2.addOnPageChangeListener(pageListener);
        vwPager3.addOnPageChangeListener(pageListener);

        btnBaslat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Basla();
            }

        });

        btnAcil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                switch (mMiniDrone.getFlyingState()) {
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_LANDED:
                        mMiniDrone.takeOff();
                        break;
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_FLYING:
                    case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_HOVERING:
                        mMiniDrone.land();
                        break;
                    default:
                }
            }
        });
    }

    public void Basla(){
        thread = new Thread(){
            @Override
            public void run(){
                try {
                    synchronized (this){
                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 50); //ileri
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 20); //sağ dön
                        sleep(1350);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 50); //sağa doğru ilerle
                        sleep(2000);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.flip(ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM.FRONT);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setYaw((byte) -20); //sola dön
                        sleep(1350);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 50); // ilerle
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) -20); //sola dönme
                        sleep(1350);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 50); //ilerle
                        sleep(1000);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.setYaw((byte) 20); //sağa  dönme
                        sleep(1350);

                        mMiniDrone.setYaw((byte) 0);

                        mMiniDrone.setFlag((byte) 1);
                        mMiniDrone.setPitch((byte) 50);
                        sleep(1500);

                        mMiniDrone.setFlag((byte) 0);
                        mMiniDrone.setPitch((byte) 0);

                        mMiniDrone.flip(ARCOMMANDS_ANIMATION_FLIP_TYPE_ENUM.BACK);

                    }
                }
                catch (Exception e){
                    Log.d("hata","xd");
                }finally {
                    mMiniDrone.land();
                    thread.interrupt();
                }
            }
        };

        mMiniDrone.takeOff();
        thread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // show a loading view while the minidrone is connecting
        if ((mMiniDrone != null) && !(ARCONTROLLER_DEVICE_STATE_ENUM.ARCONTROLLER_DEVICE_STATE_RUNNING.equals(mMiniDrone.getConnectionState())))
        {
            mConnectionProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            mConnectionProgressDialog.setIndeterminate(true);
            mConnectionProgressDialog.setMessage("Bağlanıyor ...");
            mConnectionProgressDialog.setCancelable(false);
            mConnectionProgressDialog.show();

            // if the connection to the MiniDrone fails, finish the activity
            if (!mMiniDrone.connect()) {
                finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mMiniDrone != null)
        {
            mConnectionProgressDialog = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
            mConnectionProgressDialog.setIndeterminate(true);
            mConnectionProgressDialog.setMessage("Bağlantı Kesiliyor ...");
            mConnectionProgressDialog.setCancelable(false);
            mConnectionProgressDialog.show();

            if (!mMiniDrone.disconnect()) {
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    public void onDestroy()
    {
        mMiniDrone.dispose();
        super.onDestroy();
    }

    private final MiniDrone.Listener mMiniDroneListener = new MiniDrone.Listener() {
        @Override
        public void onDroneConnectionChanged(ARCONTROLLER_DEVICE_STATE_ENUM state) {
            switch (state)
            {
                case ARCONTROLLER_DEVICE_STATE_RUNNING:
                    mConnectionProgressDialog.dismiss();
                    break;

                case ARCONTROLLER_DEVICE_STATE_STOPPED:
                    // if the deviceController is stopped, go back to the previous activity
                    mConnectionProgressDialog.dismiss();
                    finish();
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onBatteryChargeChanged(int batteryPercentage) {
            txtBatarya.setText(String.format("%d%%", batteryPercentage));
        }

        @Override
        public void onPilotingStateChanged(ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_ENUM state) {
            switch (state) {
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_LANDED:
                    btnAcil.setText("Kalk");
                    break;
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_FLYING:
                case ARCOMMANDS_MINIDRONE_PILOTINGSTATE_FLYINGSTATECHANGED_STATE_HOVERING:
                    btnAcil.setText("İn");
                    break;
                default:
                    btnAcil.setEnabled(false);
            }
        }

        @Override
        public void onPictureTaken(ARCOMMANDS_MINIDRONE_MEDIARECORDEVENT_PICTUREEVENTCHANGED_ERROR_ENUM error) {

        }

        @Override
        public void configureDecoder(ARControllerCodec codec) {

        }

        @Override
        public void onFrameReceived(ARFrame frame) {

        }

        @Override
        public void onMatchingMediasFound(int nbMedias) {

        }

        @Override
        public void onDownloadProgressed(String mediaName, int progress) {

        }

        @Override
        public void onDownloadComplete(String mediaName) {

        }


        //ARAYÜZE KENDİ TAKLALARIMIZI KOYMAK İÇİN BURAYA YAZMAMIZ LAZIM
        @Override
        public void onAnimationTypeChanged(ARCOMMANDS_ANIMATION_TYPE_ENUM type, byte percent) {

        }

        @Override
        public void onAnimationStateChanged(ARCOMMANDS_ANIMATION_STATE_ENUM state) {

        }
    };


//Sayfa değişimi
    private class PageListener implements ViewPager.OnPageChangeListener {
        private int currentPage;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        public void onPageSelected(int position) {
            currentPage = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if((vwPager1.getCurrentItem()==0)&&(vwPager2.getCurrentItem()==1)&&(vwPager3.getCurrentItem()==2))
                btnBaslat.setEnabled(true);
            else
                btnBaslat.setEnabled(false);
        }

    }
}
