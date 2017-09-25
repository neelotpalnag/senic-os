SenicOS -- The Senic Linux distribution
=======================================

Well, first of all, admittedly "SenicOS" is a bit of a mouthful - it's not actually a custom operating system, but simply a `yocto <https://www.yoctoproject.org/>`_ based **linux distribution**.

You can `find the sources on github <https://github.com/getsenic/senic-os>`_.

To bootstrap a fresh checkout of this repo make sure you are running this on an `OS supported by OpenEmbedded as a build platform <http://www.yoctoproject.org/docs/1.8/ref-manual/ref-manual.html#required-packages-for-the-host-development-system>`_ and then simply run ``make``.
This will check out the required upstream yocto repositories.

You can then perform a build with these settings on the host like so::

    source oe/oe-init-build-env

This will use (and create) the default build directory named ``build``, however, you can provide an arbitrary custom name (usually 'your' username) i.e.::

    source oe/oe-init-build-env xxx

and then perform a separate build in your own environment.


To download the build, run ``make download`` on your local instance, this will (r)sync the server's build directory to the local build directory (in essence as if you had run the build command locally) at ``build/tmp-glibc/deploy/images/senic-hub-beta/``.


Notes on flashing the image and provisioning COVI/Hub from MacOS
----------------------------------------------------------------

Specify host in your ssh config
*******************************

As a single time setup, please add the following SSH configuration to your `~/.ssh/config` file::

    Host osbuild
        Hostname osbuild.senic.com
        ForwardAgent yes
        User senic

Please note that only users who have their publick SSH keys shared with us can access the build server.

Downloading the last .wic image
*******************************

From the root folder of this repository run::

    make download


Writing the .wic image to an SD card
************************************


Depending on the device name and date of the SenicOS image, you can use the following commands to unzip and flash the image to an SD card::

    diskutil unmountDisk /dev/disk3
    bunzip2 ./build/tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta-20170621141301.rootfs.wic.bz2
    sudo dd if=build/tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta-20170621141301.rootfs.wic of=/dev/rdisk3 bs=1024k
    diskutil unmountDisk /dev/disk3

Manually performing provisioning steps that will be automated soon
******************************************************************

Using a TTY/UART cable we connect to board through its serial console, (i.e. ``screen /dev/tty.usbserial 115200``) and then run::

    nmcli dev wifi con <SSID> password <PASSWORD> ifname wlan0
    ifconfig


Debugging
---------

Run ``bitbake -c do_rootfs senic-os-dev`` to trigger a build.
Then you can check ``tmp-glibc/work/senic_hub_beta-oe-linux-gnueabi/senic-os-dev/1.0-r0/rootfs/`` and inspect what the filesystem looks like.
In many cases this is enough and you can save yourself the trouble of writing the image to disk and physically boot into it.

To mount the finished image directly on the build host (i.e. to avoid downloading, burning and booting it) in situations where it's not enough to inspect the rootfs (i.e. when debugging the partitioning layout).

First examine the image::

  fdisk -l -u tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta.sdimg 

Then mount the desired partition using the start sector as offset like below::

  sudo mount -o loop,offset=$((512*2080768)) tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta.sdimg sdcard


Configuring the wifi network manually
-------------------------------------

Normally, a freshly booted instance will come up in onboarding mode, meaning you can connect to and configure it via the app.

However, you can also connect via a serial console (i.e. ``screen /dev/tty.usbserial 115200``), login as root (without password) and connect to a wireless network like so::


-    nmcli dev wifi con <SSID> password <PASSWORD> ifname wlan0


Hardware
--------

The reset button is connected to pin 10 on the GPIO connector. When the button is pressed the GPIO pin is pulled to GND, otherwise it's pulled to SYS_3.3V.
