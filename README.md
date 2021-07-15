<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
***
***
***
*** To avoid retyping too much info. Do a search and replace for the following:
*** github_username, repo_name, twitter_handle, email, project_title, project_description
-->



<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/dangdinhtai0001/phoenix-spring-api">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Phoenix spring api</h3>

  <p align="center">
    Building a project framework that generates CRUD API.
    <br />
    <a href="https://github.com/dangdinhtai0001/phoenix-spring-api"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="">View Demo</a>
    ·
    <a href="https://github.com/dangdinhtai0001/phoenix-spring-api/issues">Report Bug</a>
    ·
    <a href="https://github.com/dangdinhtai0001/phoenix-spring-api/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
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
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

[![Product Name Screen Shot][product-screenshot]](https://example.com)

Here's a blank template to get started:
**To avoid retyping too much info. Do a search and replace with your text editor for the following:**
`github_username`, `repo_name`, `twitter_handle`, `email`, `project_title`, `project_description`


### Built With

* [Spring boot](https://github.com/spring-projects/spring-boot.git)
* [Spring security](https://github.com/spring-projects/spring-security.git)
* [Spring data JPA](https://github.com/spring-projects/spring-data-jpa.git)
* [MySQL](https://www.mysql.com/)
* [Maven](https://maven.apache.org/)




<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

You need at least the following to use:

* [Maven](https://maven.apache.org/install.html)
* [Java JDK from ORACLE](https://www.oracle.com/java/technologies/javase-downloads.html) or higher 
* [MySQL Database](https://dev.mysql.com/downloads/installer/)

High recommend

You should use java-8 and Intellij.

### Installation

1. Clone the repo
   ```sh
   git clone --branch dev-1.0.0-beta https://github.com/dangdinhtai0001/phoenix-spring-api.git
   ```
2. Create Mysql database

* run `api-services/script/MySQL/create_schema.sql` to create schema.
* run `api-services/script/MySQL/init_structure.sql` to import data structure.
* run `api-services/script/MySQL/init_data.sql` to import init data.

3. Run the app using maven

```sh
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

The app will start running at http://localhost:8090 by default.

<!-- USAGE EXAMPLES -->
## Usage

Use this space to show useful examples of how a project can be used. Additional screenshots, code examples and demos work well in this space. You may also link to more resources.

_For more examples, please refer to the [Documentation](https://example.com)_



<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/github_username/repo_name/issues) for a list of proposed features (and known issues).



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request



<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contact

Đặng Đình Tài - Email: dangdinhtai0001@gmail.com

Project Link: [https://github.com/dangdinhtai0001/phoenix-spring-api](https://github.com/dangdinhtai0001/phoenix-spring-api)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements

This repo use source code from 

* [sqlbuilder](https://github.com/jkrasnay/sqlbuilder.git)
* [TheAlgorithms/Java](https://github.com/TheAlgorithms/Java.git)




<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/dangdinhtai0001/phoenix-spring-api.svg?style=for-the-badge
[contributors-url]: https://github.com/dangdinhtai0001/phoenix-spring-api/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/dangdinhtai0001/phoenix-spring-api.svg?style=for-the-badge
[forks-url]: https://github.com/dangdinhtai0001/phoenix-spring-api/network/members
[stars-shield]: https://img.shields.io/github/stars/dangdinhtai0001/phoenix-spring-api.svg?style=for-the-badge
[stars-url]: https://github.com/dangdinhtai0001/phoenix-spring-api/stargazers
[issues-shield]: https://img.shields.io/github/issues/dangdinhtai0001/phoenix-spring-api.svg?style=for-the-badge
[issues-url]: https://github.com/dangdinhtai0001/phoenix-spring-api/issues
[license-shield]: https://img.shields.io/github/license/dangdinhtai0001/phoenix-spring-api.svg?style=for-the-badge
[license-url]: https://github.com/dangdinhtai0001/phoenix-spring-api/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/dangdinhtai0001
