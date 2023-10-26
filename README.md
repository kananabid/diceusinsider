# insidertestproject

ChromeDriver 118.0.5993.70 for MacOs is used for this test.
Tests can be started via running the testng.xml file.
Sleeps are added in between some steps in order to improve the visibility while the test is running
(Ideally, they should not be present 🌚)

Log messages added🌚
Log file now looks much better. Example:


[INFO ] 2023-10-26 12:33:52.296 [main] LoggerFile - Initializing things
[INFO ] 2023-10-26 12:33:52.330 [main] LoggerFile - Navigate to https://useinsider.com and execute test.
Actual Page Title: #1 Leader in Individualized, Cross-Channel CX — Insider
...

[INFO ] 2023-10-26 12:34:30.262 [main] LoggerFile - Test is finished. You can find results in test-output folder
[INFO ] 2023-10-26 12:34:30.607 [main] LoggerFile - Closing driver

...

[ NOTE ] - On https://useinsider.com/careers/quality-assurance/ webpage, after applying the relevant filters,
not all jobs' Position contains “Quality Assurance” - one job's title is as follows:
Software QA Tester- Insider Testinium Tech Hub (Remote) - So, no "Quality Assurance" keyword here.
This issue is handled gracefully through the ExceptionHandler class. You can check the log messages.

[ NOTE ] - Sometimes Location Filter on the Quality Assurance page does not return list of elements.
This would lead to test failure as the element (Istanbul, Turkey) is not visible.
