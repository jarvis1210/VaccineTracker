# Vaccine Availability Tracker
This is AWS Java Lambda function which checks the covid vaccine availability based on PIN for india. This function fetches the data from the api exposed by Government of India.
Details of API can found here : https://apisetu.gov.in/public/api/cowin/cowin-public-v2#/Appointment%20Availability%20APIs/findByPin 

Build & Runtime : 
1. Run "mvn clean install" to build. 
2. Minimum java 8 runtime required.


Use Case : You can use this to get regular email/sms about the availability of vaccine for the PIN number you want. This will save you time you require to manully login to cowin              site and look for vaccine availability plus you will not miss to book slot.  

Steps to acheive end to end solution : 

  Pre-requites :
  1. You need to have aws account with proper permission. You can create aws free tier account (https://aws.amazon.com/premiumsupport/knowledge-center/free-tier-charges/).
  2. Hands on experience with AWS IAM,Lambda,SNS,CloudWatch.
  
  Steps :
  1. Create a lambda funtion and upload the jar 
  2. Cretae a SNS topic and choose the email/sms subscription,the you way you want get notified.
  3. Create a CloudWatchEvent rule to shcedule the lambda.
  4. Add above created topic as Lambda destination trigger.
  5. Test the whole flow and you are good to go. 
  
 Sample Input to lambda : { "PIN": "110000" }
 
