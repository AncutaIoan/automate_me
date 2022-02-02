<div id="top"></div>




<br />
<div align="center">

  <h3 align="center">Automate me</h3>


</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Scopul aplicatiei este de a imbunatati atmosfera casei/apartamentului si de a controla volumul de lumina dintr-o incapere, oferind proprietarului posibilitatea de a avea control complet.


<p align="right">(<a href="#top">back to top</a>)</p>



### Built With

* [Java](https://www.java.com/en/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [MySQL](https://www.mysql.com/)
* [Mosquitto](https://mosquitto.org/)

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

This is an example of how you may give instructions on setting up your project locally.
To get a local copy up and running follow these simple example steps.

### Prerequisites

MySQL 
Any endpoint testing IDE ( for example: Postman)
Installing Mosquitto 


### Installation

_Below is an example of how you can instruct your audience on installing and setting up your app. This template doesn't rely on any external dependencies or services._

1. Get a free API Key at [https://openweathermap.org/api](https://example.com)
2. Create MySQL database using any IDE ( MySQL Workbench).
3. Open postman and write endpoints for testing.
4. Install Mosquitto for your machine and open server locally.Modify config.conf
      ```sh
       listener 1883
       allow_anonymous true
       ```
   And Start server in cmd:
   ```sh
        mosquitto -v -c config.conf
   ```
5. Modify resources/application.properties and security.properties 
    ```sh
   #application.properties
   server.port=8081
   spring.datasource.url = jdbc:mysql://localhost:3306/automate_me
   spring.datasource.username = root
   spring.datasource.password = password
   spring.jpa.hibernate.ddl-auto = update
   spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
   
   #application.security
   
   security.tokenExpirationTime = 600000
   security.secret = ANA ARE MERE
   security.tokenPrefix = Bearer
   security.headerString = Authorization
   ```
6. Clone the repo
   ```sh
   git clone https://github.com/AncutaIoan/automate_me.git
   ```


<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

Register endpoint
![alt text](https://github.com/AncutaIoan/automate_me/blob/main/images_doc/register.png)

Login endpoint 
![alt text](https://github.com/AncutaIoan/automate_me/blob/main/images_doc/login.png)

Get all users endpoint
![alt text](https://github.com/AncutaIoan/automate_me/blob/main/images_doc/get_all_users.png)

Get weather endpoint
![alt text](https://github.com/AncutaIoan/automate_me/blob/main/images_doc/get_weather.png)

Add window endpoint 
![alt text](https://github.com/AncutaIoan/automate_me/blob/main/images_doc/addWindow.png)

Set position endpoint 
![alt text](https://github.com/AncutaIoan/automate_me/blob/main/images_doc/set_position.png)




<p align="right">(<a href="#top">back to top</a>)</p>
 


<!-- ROADMAP -->
## Roadmap



See the [open issues](https://github.com/AncutaIoan/automate_me/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#top">back to top</a>)</p>









<!-- CONTACT -->
## Contact


Project Link: [https://github.com/AncutaIoan/automate_me](https://github.com/your_username/repo_name)

<p align="right">(<a href="#top">back to top</a>)</p>






