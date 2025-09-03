1. Requirement
			## Tech Stack :
				- **Java 17**
				- **Appium 2.11.2+**
				- **Node v24.4.1 **
				- **Xcode**
				- **Eclipse IDE / Intelij IDE**
			
			## Automation Tools & Server :
				- **Selenium**
				=  **Appium**
			
			## Framework = :
				- **TestNG**
			
			## Reporter :
				- **Extent Reports**

2. Setup Environment
			## Install Requirement :
				- [Java JDK 17](https://adoptium.net/)
				- [Maven](https://maven.apache.org/)
				- [Node.js](https://nodejs.org/) & [Appium](https://appium.io/)
				  ```bash
				  npm install -g appium
				  ```
				- Xcode  Emulator
				- Real Device (Jika run menggunakan Real Device)

3. Clone Repository
 N/A

4. Cara Menjalankan Test
			### Di Class AppiumServerManager.java : 
				private static File nodePath = new File("/Users/kennyramadhan/.nvm/versions/node/v24.4.1/bin/node");
				private static File appiumJSPath = new File("/usr/local/bin/appium");
				Pastikan untuk mengganti path tersebut sesuai dengan path di local machine 
				
			### Cara Mengetahui kedua path tersebut adalah via terminal :
					 Node = which node
					 Appium = which appium
					 
			### Setelah path sudah diganti dan benar selanjut nya dapat menjalanka test dengan dua cara yaitu :
					Via Terminal :
						Jalankan Semua Test Sesuai pom.xml = mvn clean test
						Jalankan Test dengan Menentukan File Suite = mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
						Jalankan Test dengan Filter Berdasarkan Nama Class = mvn clean test -Dtest=NamaClassTest
						Jalankan Test dengan Filter Berdasarkan Method = mvn clean test -Dtest=NamaClassTest#namaMethod
						Skip Test Tapi Tetap Compile = mvn clean install -DskipTests
						Debug Output = mvn clean test -X atau mvn clean test -e
					Via Eclipse :
						Klik Kanan file testng.xml lalu Run As TestNG Suite	

5. Struktur Project
			##	src/test/java			
						Berisi Test Case dan Listeners Untuk Reports
			##	src/main/java	:		
						Berisi Config Menjalankan Appium Server Dan Shutdown Appium Server
						Berisi Page Object yang akan digunakan di Test Case 
						Berisi File Dependency Appium yang akan digunakan saat Start Appium
			
			## pom.xml :              
						Maven dependencies & build config
						
			## testng.xml  :           
						TestNG suite & listener config
			     
6. Penjelasan File Penting

				- **File BaseTest** → Setup & teardown yang akan di jalankan di semua test case
				- **Package MBI.DST.Pages/** → Implementasi Page Object Model (misalnya Login)
				- **File ConfigLoader** → Loader konfigurasi dari file `.properties`
				- **File Utils** → Custom  Function yang digunakan di Pages seperti verifyElementExist,tapWhenElementVisible dll.
				- **File AppiumServerManager** → Start/stop Appium server otomatis
				- **Package MBI.DST.Listeners** → Custom listener untuk logging, screenshot, dan setup report HTML dengan Extent Reports
				- **Package MBI.DST.Mobile** → Berisi Test Case yang akan di tulis 
				
7. Hasil Report & Screenshot
			- reports/
   			- reports/pass
   			- reports/fail
   	
			  ```
9. Alur Eksekusi

				1. Jalankan `testng.xml`
				2. Listener aktif (logging, screenshot, report)
				3. `BaseTest` inisialisasi driver
				4. Eksekusi test case via `Pages` (POM)
				5. Hasil tersimpan di report folder
10. Catatan
				- Pastikan device/emulator aktif sebelum menjalankan mobile test.
				- Jika ingin menggunakan real device pastikan WDA sudah terinstall di device
				- Pastikan path node dan appium sudah di ganti sesuai dengan local machine
