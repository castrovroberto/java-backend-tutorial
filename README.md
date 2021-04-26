# Java Simple Daemon Tutorial

This a simple backend daemon process written in Java.
This is part of a series of assignments made to serve as an example on Backend Development with Java.
Special focus is put on high performance batch processess that must handle large amounts of files, records or both.

## Getting Started

Feel free to clone, fork or download the code and compile it in your environment.
This project is not intended for production-like systems. 
No ready-to-deliver-package will be available for now.

### Prerequisites

You need Java installed on your system.
That's it!

This should do it:
```
sudo apt-get install openjdk-8-jre
```

### Installing

Modify the start_engine.sh script to set up environment variable $HPDL_BASE_DIR with the path of your local directory.

Example:
```
export HPDL_BASE_DIR="/home/roberto/Projects/Tuts/java-backend-tutorial/hpdl"
```

Compile application:
```
cd $HPDL_BASE_DIR/src/
make all 
```

Clean up:
```
cd $HPDL_BASE_DIR/src/
make clean 
```

To run the application
```
sh start_engine.sh
```

To stop the application
```
sh stop_engine.sh
```

## Authors

* **Roberto Castro** - *Initial work* - [castrovroberto](https://https://github.com/castrovroberto)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE) file for details

## Acknowledgments

* Had the inspiration for making and sharing this project for the time invested working on telecommunications
* and how effective it was to handle lots of data.

