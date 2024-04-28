<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif">

<div align="center">
  <img  src="https://github.com/Electromayonaise/Electromayonaise/blob/main/Assets/github-contribution-grid-snake%20blacktest(1).svg"
       alt="snake" /></a>
</div>

# ICEMerge

## *Project Members: [Martín Gómez](https://github.com/Electromayonaise), [Julio Prado](https://github.com/jul109), [Daniel Plazas](https://github.com/DanielJPC19)*

Distributed merge sort with async calls, using the ICE middleware. 

```bash
ICEBucket
├── client
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── Client.java
│   │   │   └── resources
│   │   │       └── client.config
├── primaryServer
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   ├── PrimaryServer.java
│   │   │   │   └── Merge.java
│   │   │   └── resources
│   │   │       └── primaryServer.config
└── helperServer
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── HelperServer.java
    │   │   │   └── MergeSort.java
    │   │   └── resources
    │   │       └── helperserver.config

This project structure only contains the excecutables
```

## *How to run the project*

### 1. Clone the repository

```bash
git clone https://github.com/Electromayonaise/ICEBucket.git
```

### 2. Compile the project

To do so, simply click on the `compile.bat` file. This will compile the project and generate the .jar files, which are the executables.
When running the compile.bat file, a terminal will open for a few seconds, and then close. This is normal. 

<div align="center">
  <img  src="https://github.com/Electromayonaise/ICEMerge/blob/main/doc/InstructionAssets/compile.png"
       alt="compile" /></a>
</div>

### 3. Run the project

To run the project, you will need to run the executables in the following order:

1. Run the primary server by clicking on the `executePrimaryServer.bat` file. This will open a terminal that will show no output, but it is running.
<div align="center">
  <img  src="https://github.com/Electromayonaise/ICEMerge/blob/main/doc/InstructionAssets/executePrimaryServer.png"
       alt="executePrimaryServer" /></a>
</div>


3. Run the helper server by clicking on the `executeHelperServer.bat` file. This will open a terminal that will say "secondary server init", plus the IP and Port of the server. The primary server will also show a message saying that the helper server has connected, and the IP and Port of the helper server.
<div align="center">
  <img  src="https://github.com/Electromayonaise/ICEMerge/blob/main/doc/InstructionAssets/executeHelperServer.png"
       alt="executeHelperServer" /></a>
</div>

4. Run the client by clicking on the `executeClient.bat` file. This will open a terminal that will show the output of the client.
<div align="center">
  <img  src="https://github.com/Electromayonaise/ICEMerge/blob/main/doc/InstructionAssets/executeClient.png"
       alt="executeClient" /></a>
</div>

### 4. Enjoy the project

On the terminal that shows the output of the client, you will get the option to either enter a file path for the program to sort, or sort one of the test files that are included in the project. 
If you choose the first, make sure to enter the path correcly and that the file exists. If you choose the second, you will get the output of the sorting process directly after choosing one of the test files.

Please do note that right now, the project is configured to sort integers. If you want to sort other types of data, you will need to modify the code accordingly. 

It is also configured for everything to run in localhost, so you will need to modify the configuration files if you want to run the project in different machines, which will be relatively strightforward thanks to ICE, you will only need to `change the IP addresses in the configuration files`.

<img src="https://user-images.githubusercontent.com/73097560/115834477-dbab4500-a447-11eb-908a-139a6edaec5c.gif">
