# TA_Booking_UI
Task no.2 - UI Test for booking flight via page: https://www.skyscanner.net/

NOTE: They may be issue with running test on firefox browser (issue with geckodriver).
To run test on firefox, please add environment variable to geckodriver manually or by command in cmd: $ set PATH=%PATH%;C:\YOUR_LOCATION\geckodriver
more details about geckodriver on below link:
https://github.com/mozilla/geckodriver

Test may run on different browser, but please remember to change line in test:
    WebDriver driver = new FirefoxDriver();
    to i.e.
    WebDriver driver = new ChromeDriver();
   

