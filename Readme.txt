1. Download All Files Need
  1.1 Download Jmeter 
  1.2 Download Selenium/WebDriver Support on Jmeter plugin page (https://jmeter-plugins.org/) 
    1.2.1 Copy files in lib and add them to Jmeter -> apache-jmeterxx -> lib folder.
    1.2.2 If there's files in ext folder, copy and add them to Jmeter -> apache-jmeterxx -> lib -> ext folder.
  1.3 Download Webdriver.exe   In this JavaJmeter file, I use Firefox browser; thus, I need geckodriver.exe
    Note:  Optional: you can add that to Environment Variable Path
           Optional:  If there's WebDriverManager jar available in the market, we add to Java library and we don't need this step 3.
  1.4  Download Seleium-server-satandalone

2. Once clone the repo to your local, you need to add these folders to your library
  2.1 ApacheJMeter_core - it locates under Apache folder -> lib - ext folder
  2.2 ApacheJMeter_Java - same location as 2.1
  2.3 Selenium-server-standalone

3. After finishing with implementation we have to export the file.jar and save it under JMeter ext folder. 

4. Export/Import Java Project  
  4.1 Export the project 
    - Implement auction round 1 using Java project interface JavaSamplerCilent.  More detail:  https://jmeter.apache.org/api/org/apache/jmeter/protocol/java/sampler/JavaSamplerClient.html
    - Go to Project Structure -> Modules -> Dependencies -> Jar
    - Go to Build Artifact -> click Build
  4.2 Copy the jar file under output directory where you save the Jar file.  Then, add it to Jmeter -> apache-jmeterxx -> lib -> ext folder.

5. Open Jmeter (If it's currently open, it needs restarting)
  5.1 Create Thread Group
  5.2 Under Thread Group, add JavaRequest
  5.3 Setup parameter username, password under JavaRequest
  5.4 Add CSV Data Set Config and set path to excel file where we store usernames, passwords
 

