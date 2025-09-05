# Planit Automation Assessment – Selenium + Maven + TestNG

This project automates **Jupiter Toys** demo site using:
- Selenium 4 + TestNG + Page Object Model
- WebDriverManager (auto driver)
- ExtentReports (HTML + screenshots on failure)
- GitHub Actions + Jenkins pipelines

## Run Locally
```bash
mvn clean test -Dheadless=true
```
Report: `target/ExtentReport.html`  
Screenshots: `target/screenshots/`

## CI/CD
- GitHub Actions: auto-runs, uploads `ExtentReport.html` and screenshots as artifact.
- Jenkins: use `Jenkinsfile` provided.
