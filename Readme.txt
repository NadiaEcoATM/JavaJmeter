1. Download All Files Need
  1.1 Download Jmeter 
  1.2 Download Selenium/WebDriver Support on Jmeter plugin page (https://jmeter-plugins.org/) 
    - Copy files in lib and add them to Jmeter -> apache-jmeterxx -> lib folder.
    - If there's files in ext folder, copy and add them to Jmeter -> apache-jmeterxx -> lib -> ext folder.
  1.3 Download Webdriver.exe   In this JavaJmeter file, I use Firefox browser; thus, I need geckodriver.exe
    Note:  Optional: you can add that to Environment Variable Path
           Optional:  If there's WebDriverManager jar available in the market, we add to Java library and we don't need this step 3.

2. Export/Import Java Project
  2.1 Export the project 
    - Go to Project Structure -> Modules -> Dependencies -> Jar
    - Go to Build Artifact -> click Build
  2.2 Copy the jar file under output directory where you save the Jar file.  Then, add it to Jmeter -> apache-jmeterxx -> lib -> ext folder.

3. Open Jmeter (If it's currently open, it needs restarting)
  3.1 Create Thread Group
  3.2 Under Thread Group, add JavaRequest
 
