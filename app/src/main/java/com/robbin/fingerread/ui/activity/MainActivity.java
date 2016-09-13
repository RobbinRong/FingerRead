package com.robbin.fingerread.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.robbin.fingerread.R;
import com.robbin.fingerread.ui.fragment.BaseCollectFragment;
import com.robbin.fingerread.ui.fragment.BaseReadFragment;
import com.robbin.fingerread.ui.fragment.BaseScienceFragment;
import com.robbin.fingerread.ui.fragment.DailyFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivityWithNoSwip {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    public AccountHeader header;
    public Drawer drawer;
    private Fragment currentFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction fragmentTransaction;

    @Override
    protected int getLayoutId() {return R.layout.activity_main;}

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
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
                .withAccountHeader(header).withSliderBackgroundColor(ContextCompat.getColor(this,R.color.white))
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.daily).withIcon(R.mipmap.ic_home).withIdentifier(R.mipmap.ic_home).withTextColor(ContextCompat.getColor(this,R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.read).withIcon(R.mipmap.ic_reading).withIdentifier(R.mipmap.ic_reading).withTextColor(ContextCompat.getColor(this,R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.science).withIcon(R.mipmap.ic_science).withIdentifier(R.mipmap.ic_science).withTextColor(ContextCompat.getColor(this,R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.collect).withIcon(R.mipmap.ic_collect_grey).withIdentifier(R.mipmap.ic_collect_grey).withTextColor(ContextCompat.getColor(this,R.color.text_color)),
                        new PrimaryDrawerItem().withName(R.string.about).withIcon(R.mipmap.ic_about).withIdentifier(R.mipmap.ic_about).withTextColor(ContextCompat.getColor(this,R.color.text_color))
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
    }
}
