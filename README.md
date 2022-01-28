# adjust


Setup Guide

1- Download Webdriver (Chrome or Firefox)

  - ChromeDriver from https://chromedriver.chromium.org/downloads
  - OR
  - GeckoDriver from https://github.com/mozilla/geckodriver/releases

2- Create /opt directory
  - mkdir -P /opt
  - chmod -R 777 /opt

3- Copy your webdriver into /opt directory

4- Give webdriver security permission in MACOS
  - cd /opt
  - xattr -d com.apple.quarantine chromedriver
  - OR
  - xattr -d com.apple.quarantine geckodriver

5- Following VM Arguments are Optional : 

*You can run tests in parallel, crossbrowser supported [chrome, firefox] or use browser in headless mode

-Djunit.jupiter.execution.parallel.enabled=true
-Djunit.jupiter.execution.parallel.config.strategy=fixed
-Djunit.jupiter.execution.parallel.config.fixed.parallelism=4
-Djunit.jupiter.execution.timeout.test.method.default=10m
-Dwebdriver.remote.option=false
-Dwebdriver.chrome.headless=false
-Dbrowser.type=chrome
