package com.devash.uninstall_apps;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.PackageInstaller;

import java.io.File;
import 	android.app.PendingIntent;
import android.app.Activity;
import android.content.Context;


/** UninstallAppsPlugin */
public class UninstallAppsPlugin implements MethodCallHandler {
   MethodChannel channel;
   Activity activity;




  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "uninstall_apps");
    channel.setMethodCallHandler(new UninstallAppsPlugin(registrar.activity(),channel));
  }

  UninstallAppsPlugin(Activity activity,MethodChannel channel){

    this.activity=activity;
    this.channel=channel;
    this.channel.setMethodCallHandler(this);
      
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
     
     if(call.method.equals("Uninstall")){
   
           Runtime.getRuntime().exec("dpm set-device-owner com.example.deviceowner/.MyDeviceAdminReceiver");
        
     
           String appPackage = call.argument("App");
           Intent intent = new Intent(activity, activity.getClass());
           PendingIntent sender = PendingIntent.getActivity(activity, 0, intent, 0);
           PackageInstaller mPackageInstaller = activity.getPackageManager().getPackageInstaller();
           mPackageInstaller.uninstall(appPackage, sender.getIntentSender());
     }
    
    else {
      result.notImplemented();
    }


  }
}
