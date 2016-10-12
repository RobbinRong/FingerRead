package com.robbin.fingerread.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.robbin.fingerread.R;
import com.robbin.fingerread.constant.Settings;
import com.robbin.fingerread.ui.fragment.BaseCollectFragment;
import com.robbin.fingerread.ui.fragment.BaseReadFragment;
import com.robbin.fingerread.ui.fragment.BaseScienceFragment;
import com.robbin.fingerread.ui.fragment.DailyFragment;
import com.robbin.fingerread.utils.Utils;

import butterknife.Bind;

public class MainActivity extends BaseActivityWithNoSwip {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    public AccountHeader header;
    public Drawer drawer;
    private Fragment currentFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;
    private Settings mSettings = Settings.getInstance();
    private int mLang = -1;

    @Override
    protected int getLayoutId() {
        Settings.isNightMode = mSettings.getBoolean(Settings.NIGHT_MODE, false);

        // change Brightness
//        if(mSettings.isNightMode && Utils.getSysScreenBrightness() > CONSTANT.NIGHT_BRIGHTNESS){
//            Utils.setSysScreenBrightness(CONSTANT.NIGHT_BRIGHTNESS);
//        }else if(mSettings.isNightMode == false && Utils.getSysScreenBrightness() == CONSTANT.NIGHT_BRIGHTNESS){
//            Utils.setSysScreenBrightness(CONSTANT.DAY_BRIGHTNESS);
//        }

        if(Settings.isNightMode){
            this.setTheme(R.style.NightTheme);
        }else{
            this.setTheme(R.style.DayTheme);
        }
        return R.layout.activity_main;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mLang = Utils.getCurrentLanguage();
        if (mLang > -1) {
            Utils.changeLanguage(this, mLang);
        }
        init();
        currentFragment=new DailyFragment();
        switchFragment();
    }

    private void init() {
        setSupportActionBar(toolbar);
        header=new AccountHeaderBuilder().withActivity(this)
                .withCompactStyle(false).withHeaderBackground(R.drawable.header)
                .addProfiles(new ProfileDrawerItem().withIcon(R.drawable.user_logo).
                        withName(getString(R.string.user_ame)).withEmail(getString(R.string.user_email)))
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean current) {
                        Intent i = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(i);
                        return false;
                    }
                }).build();
        drawer=new DrawerBuilder().withActivity(this).withToolbar(toolbar).withActionBarDrawerToggle(true)
                .withAccountHeader(header).withSliderBackgroundColor(Settings.isNightMode ? ContextCompat.getColor(this, R.color.night_primary) : ContextCompat.getColor(this, R.color.white))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.daily).withIcon(R.mipmap.ic_home).withIdentifier(R.mipmap.ic_home).withTextColor(Settings.isNightMode ? ContextCompat.getColor(this, R.color.white) : ContextCompat.getColor(this, R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.read).withIcon(R.mipmap.ic_reading).withIdentifier(R.mipmap.ic_reading).withTextColor(Settings.isNightMode ? ContextCompat.getColor(this, R.color.white) : ContextCompat.getColor(this, R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.science).withIcon(R.mipmap.ic_science).withIdentifier(R.mipmap.ic_science).withTextColor(Settings.isNightMode ? ContextCompat.getColor(this, R.color.white) : ContextCompat.getColor(this, R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.collect).withIcon(R.mipmap.ic_collect_grey).withIdentifier(R.mipmap.ic_collect_grey).withTextColor(Settings.isNightMode ? ContextCompat.getColor(this, R.color.white) : ContextCompat.getColor(this, R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.about).withIcon(R.mipmap.ic_about).withIdentifier(R.mipmap.ic_about).withTextColor(Settings.isNightMode ? ContextCompat.getColor(this, R.color.white) : ContextCompat.getColor(this, R.color.text_color)),
                        new SectionDrawerItem().withName(R.string.app_name).withTextColor(Settings.isNightMode ? ContextCompat.getColor(this, R.color.white) : ContextCompat.getColor(this, R.color.text_color)),
                        new SecondaryDrawerItem().withName(Settings.isNightMode == true ? R.string.text_day_mode: R.string.text_night_mode)
                        .withIcon(Settings.isNightMode == true?R.mipmap.ic_day_white:R.mipmap.ic_night).withIdentifier(R.mipmap.ic_night)
                        .withTextColor(Settings.isNightMode?ContextCompat.getColor(this, R.color.white):ContextCompat.getColor(this,R.color.text_light))
                        ,new SecondaryDrawerItem().withName(R.string.text_language)
                                .withIcon(Settings.isNightMode == true?R.mipmap.ic_day_white:R.mipmap.ic_night).withIdentifier(R.mipmap.ic_launcher)
                                .withTextColor(Settings.isNightMode?ContextCompat.getColor(this, R.color.white):ContextCompat.getColor(this,R.color.text_light))
                          ).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                           switch (drawerItem.getIdentifier()){
                              case R.mipmap.ic_home:
                                    if (currentFragment instanceof DailyFragment)
                                      return  false;
                                currentFragment=new DailyFragment();
                                break;
                            case R.mipmap.ic_reading:
                                if (currentFragment instanceof BaseReadFragment)
                                    return  false;
                                currentFragment=new BaseReadFragment();
                                break;
                            case R.mipmap.ic_science:
                                if (currentFragment instanceof BaseScienceFragment)
                                    return  false;
                                currentFragment=new BaseScienceFragment();
                                break;
                            case R.mipmap.ic_collect_grey:
                                if (currentFragment instanceof BaseCollectFragment)
                                    return  false;
                                currentFragment=new BaseCollectFragment();
                                break;
                            case R.mipmap.ic_about:
                                Intent toAbout = new Intent(MainActivity.this, AboutActivity.class);
                                startActivity(toAbout);
                                return  false;
                            case R.mipmap.ic_night:
                                   Settings.isNightMode = !Settings.isNightMode;
                                   mSettings.putBoolean(mSettings.NIGHT_MODE, Settings.isNightMode);
                                   MainActivity.this.recreate();
                                   return false;
                            case R.mipmap.ic_launcher:

                                   showLangDialog();
                                   return false;
                        }
                        switchFragment();
                        return false;
                    }
                }).build();


    }

    private void switchFragment() {
        if(currentFragment instanceof DailyFragment){
            switchFragment(currentFragment, getString(R.string.daily));
        }else if(currentFragment instanceof BaseReadFragment){
            switchFragment(currentFragment, getString(R.string.read));
        }else if(currentFragment instanceof BaseScienceFragment){
            switchFragment(currentFragment, getString(R.string.science));
        }
        else if(currentFragment instanceof BaseCollectFragment){
            switchFragment(currentFragment, getString(R.string.collect));
        }
    }
    private void switchFragment(Fragment fragment,String title){
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_content, fragment);
        fragmentTransaction.commit();
        getSupportActionBar().setTitle(title);
    } @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen()){
            drawer.closeDrawer();
        }else{
            super.onBackPressed();
        }
    }
    private void showLangDialog() {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.text_language))
                .setSingleChoiceItems(
                        getResources().getStringArray(R.array.langs), Utils.getCurrentLanguage(),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (which != Utils.getCurrentLanguage()) {
                                    mSettings.putInt(Settings.LANGUAGE, which);
                                    Settings.needRecreate = true;
                                }
                                dialog.dismiss();
                                if (Settings.needRecreate) {
                                    MainActivity.this.recreate();
                                }
                            }
                        }
                ).show();

    }

}
