# Vaccine Availability Tracker
This is aws java lambda function which checks the covid vaccine availability for PIN in india.

Build :  run mvn clean install.
Upload fat jar created after build in aws lamda function.
minimum java 8 runtime required.
PIN can be changed in the code for which vaccine availability is tracked for.

Use Case : You can use this to get regular email/sms about the availability of vaccine for the PIN number you want. This will save you time you require to manully login to cowin              site and look for vaccine availability plus you will not miss to book slot.  

Steps to acheive end to end solution : 
  Pre-requites : 1. You need to have aws account with proper permission. You can create aws free tier account
                    (https://aws.amazon.com/premiumsupport/knowledge-center/free-tier-charges/) for this. 
                 2. Knowledge of AWS.
  
  1. Create a lambda funtion and upload the jar 
  2. Cretae a SNS topic and choose the email/sms subscription,the you way you want get notified.
  3. Create a CloudWatchEvent rule to shcedule the lambda.
  4. Test the whole flow and you are good to go. 
