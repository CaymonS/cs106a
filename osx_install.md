<h1> Installing WildBeast on OS X </h1>

Welcome to the installation guide for WildBeast on Linux! In this guide, we'll walk you through the installation and deployment process for the WildBeast bot.

<h2>Prerequisites</h2>
* OS X (ElCapitan) or higher.

<h2>Preparing the environment</h2>
First off, Open the App Store and check for the latest versions. It's highly recommended that you update to at least ElCapitan before continuing with setting up WildBeast.
```
xcode-select --install
cd ~/Downloads
curl -O http://ftp.gnu.org/gnu/wget/wget-1.15.tar.gz
tar -zxvf wget-1.15.tar.gz
cd wget-1.15/
./configure
