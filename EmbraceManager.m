#import "EmbraceManager.h"
#import <Embrace/Embrace.h>

#if __has_include(<CodePush/CodePush.h>)
#import <CodePush/CodePush.h>
#endif

@implementation EmbraceManager

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(endAppStartup) {
  [[Embrace sharedInstance] endAppStartup];
}

RCT_EXPORT_METHOD(setUserIdentifier:(NSString*)userIdentifier) {
  [[Embrace sharedInstance] setUserIdentifier:userIdentifier];
}

RCT_EXPORT_METHOD(setUsername:(NSString*)username) {
  [[Embrace sharedInstance] setUsername:username];
}

RCT_EXPORT_METHOD(setUserEmail:(NSString*)userEmail) {
  [[Embrace sharedInstance] setUserEmail:userEmail];
}

RCT_EXPORT_METHOD(clearUserEmail) {
  [[Embrace sharedInstance] clearUserEmail];
}

RCT_EXPORT_METHOD(clearUserIdentifier) {
  [[Embrace sharedInstance] clearUserIdentifier];
}

RCT_EXPORT_METHOD(clearUsername) {
  [[Embrace sharedInstance] clearUsername];
}

RCT_EXPORT_METHOD(logBreadcrumb:(NSString*)message) {
  [[Embrace sharedInstance] logBreadcrumbWithMessage:message];
}

RCT_EXPORT_METHOD(startMomentWithName:(NSString*)name) {
  [[Embrace sharedInstance] startMomentWithName:name];
}

RCT_EXPORT_METHOD(startMomentWithNameAndIdentifier:(NSString*)name identifier:(NSString*)identifier) {
  [[Embrace sharedInstance] startMomentWithName:name identifier:identifier];
}

RCT_EXPORT_METHOD(startMomentWithNameAndIdentifierAndProperties:(NSString*)name identifier:(NSString*)identifier properties:(NSDictionary*)properties) {
  [[Embrace sharedInstance] startMomentWithName:name identifier:identifier properties:properties];
}

RCT_EXPORT_METHOD(startMomentWithNameAllowingScreenshot:(NSString*)name allowScreenshot:(BOOL)allowScreenshot) {
  [[Embrace sharedInstance] startMomentWithName:name identifier:nil allowScreenshot:allowScreenshot];
}

RCT_EXPORT_METHOD(startMomentWithNameAndIdentifierAllowingScreenshot:(NSString*)name identifier:(NSString*)identifier allowScreenshot:(BOOL)allowScreenshot) {
  [[Embrace sharedInstance] startMomentWithName:name identifier:identifier allowScreenshot:allowScreenshot];
}

RCT_EXPORT_METHOD(startMomentWithNameAndIdentifierAndPropertiesAllowingScreenshot:(NSString*)name identifier:(NSString*)identifier properties:(NSDictionary*)properties allowingScreenshot:(BOOL)allowingScreenshot) {
  [[Embrace sharedInstance] startMomentWithName:name identifier:identifier allowScreenshot:allowingScreenshot properties:properties];
}

RCT_EXPORT_METHOD(endMomentWithName:(NSString*)name) {
  [[Embrace sharedInstance] endMomentWithName:name];
}

RCT_EXPORT_METHOD(endMomentWithNameAndIdentifier:(NSString*)name identifier:(NSString*)identifier) {
  [[Embrace sharedInstance] endMomentWithName:name identifier:identifier];
}

RCT_EXPORT_METHOD(setUserPersona:(NSString*)persona) {
  [[Embrace sharedInstance] setUserPersona:persona];
}

RCT_EXPORT_METHOD(clearUserPersona:(NSString*)persona) {
  [[Embrace sharedInstance] clearUserPersona:persona];
}

RCT_EXPORT_METHOD(clearAllUserPersonas) {
  [[Embrace sharedInstance] clearAllUserPersonas];
}

-(EMBSeverity)severityFromString:(NSString*)inputString {
  if ([inputString isEqualToString:@"info"]) {
    return EMBSeverityInfo;
  } else if ([inputString isEqualToString:@"warning"]) {
    return EMBSeverityWarning;
  }
  return EMBSeverityError;
}

RCT_EXPORT_METHOD(logMessageWithSeverity:(NSString*)message severity:(NSString*)severity) {
  [[Embrace sharedInstance] logMessage:message withSeverity:[self severityFromString:severity]];
}


// TODO: Update this to take in screenshot flag and stack trace.
RCT_EXPORT_METHOD(
  logMessageWithSeverityAndProperties:(NSString*)message
                             severity:(NSString*)severity
                           properties:(NSDictionary*)properties
                       takeScreenshot:(BOOL)takeScreenshot
                         jsStackTrace:(NSString *)jsStackTrace
) {
    [[RNEmbrace sharedInstance] logMessage:message withSeverity:[self severityFromString:severity] properties:properties takeScreenshot:takeScreenshot jsStackTrace:jsStackTrace];
}

RCT_EXPORT_METHOD(logHandledError:(NSString*)message jsStackTrace:(NSString *)jsStackTrace) {
    [[RNEmbrace sharedInstance] logMessage:message withSeverity:EMBSeverityError properties:nil takeScreenshot:NO jsStackTrace:jsStackTrace wasHandled:YES];
}

RCT_EXPORT_METHOD(startView:(NSString*)viewName) {
    SEL selector = NSSelectorFromString(@"startViewWithName:");
    Embrace *instance = [Embrace sharedInstance];
    if ([instance respondsToSelector:selector]) {
        IMP imp = [instance methodForSelector:selector];
        ((void (*)(id, SEL, NSString *))imp)(instance, _cmd, viewName);
    } else {
      NSLog(@"Custom Views Not Supported");
    }
}

RCT_EXPORT_METHOD(endView:(NSString*)viewName) {
    SEL selector = NSSelectorFromString(@"endViewWithName:");
    Embrace *instance = [Embrace sharedInstance];
    if ([instance respondsToSelector:selector]) {
        IMP imp = [instance methodForSelector:selector];
        ((void (*)(id, SEL, NSString *))imp)(instance, _cmd, viewName);
    } else{
      NSLog(@"Custom Views Not Supported");
    }
}

RCT_EXPORT_METHOD(logUnhandledJSException:(NSString *)name message:(NSString *)message type:(NSString *)type stackTrace:(NSString *)stackTrace) {
  [[RNEmbrace sharedInstance] logUnhandledJSException:name message:message type:type stackTrace:stackTrace];
}

RCT_EXPORT_METHOD(setJavaScriptPatchNumber:(NSString *)number) {
  [[RNEmbrace sharedInstance] setJavaScriptPatchNumber:number];
}

RCT_EXPORT_METHOD(setReactNativeVersion:(NSString *)version) {
    [[RNEmbrace sharedInstance] setReactNativeVersion:version];
}

RCT_EXPORT_METHOD(checkAndSetCodePushBundleURL) {
#if __has_include(<CodePush/CodePush.h>)
    NSURL *url = [CodePush bundleURL];
    [[RNEmbrace sharedInstance] setJavaScriptBundleURL:url.path];
#endif
}

RCT_EXPORT_METHOD(addSessionProperty:(NSString*)key value:(NSString*)value permanent:(BOOL)permanent resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
  BOOL success = [[Embrace sharedInstance] addSessionProperty:value withKey:key permanent:permanent];
  resolve(@(success));
}

RCT_EXPORT_METHOD(removeSessionProperty:(NSString*)key) {
   [[Embrace sharedInstance] removeSessionPropertyWithKey:key];
}

RCT_EXPORT_METHOD (getSessionProperties:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
  NSDictionary *props = [[Embrace sharedInstance] getSessionProperties];
  resolve(props);
}

@end
