SenicOS -- The Senic Linux distribution
=======================================

Well, first of all, admittedly "SenicOS" is a bit of a mouthful - it's not actually a custom operating system, but simply a `yocto <https://www.yoctoproject.org/>`_ based **linux distribution**.

You can `find the sources on github <https://github.com/getsenic/senic-os>`_.


Building SenicOS on a remote host
---------------------------------

The ``Makefile`` supports both running the build tool chain locally on a suitable Linux machine that is able to build the OS as well as running the checkout on a non-Linux machine (typically MacOS or FreeBSD) and control the actual building on a remote host.

To this end the ``Makefile`` is currently hard coded to perform these actions on and against a host named ``osbuild``. It then falls into the responsibility of the user to provide a suitable SSH configuration for such host.

To update the host with local (uncommitted changes) run ``make upload``.

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

Connect via TTY (i.e. ``screen /dev/tty.usbserial 115200``) and then run::

    nmcli dev wifi con <SSID> password <PASSWORD> ifname wlan0
    ifconfig

Update section `plain-instance:beta` of `senic-hub/deployment/etc/ploy.conf` with::

    [plain-instance:beta]
    ....
    ip = <HUB's IP ADDRESS>

Now from `senic-hub/deployment` run::

    `bin/ploy configure beta`

If we want to put the hub into delivery state, we want to stop all daemons, delete all logs and unprovision Wi-Fi::

    supervisorctl stop all
    rm /srv/senic_hub/data/*
    nmcli con
    nmcli con del <CONNECTION NAME FROM PREVIOUS STEP>


Notes on controlling the build host from FreeBSD
------------------------------------------------

Writing the .wic image using a USB card reader
**********************************************

First, make sure to get the the device name of the memory card reader (usually ``da0``) by running ``camcontrol devlist``.

Once downloaded use ``dd`` to flash the ``.wic`` image. Please make sure to provide a sufficiently large blocksize, i.e. 1024k::

    sudo dd if=build/tmp-glibc/deploy/images/senic-hub-beta/senic-os-dev-senic-hub-beta.wic of=/dev/da0 bs=1024k


Connecting to the serial console
================================

Check the device name of the serial adapter by performing a diff between the contents of ``ls -a /dev/`` before and after plugging in the adapter (usually ``ttyU0``). Then use ``screen`` (either as root or with ``sudo``) to connect to a shell::

    sudo screen /dev/ttyU0 115200 -L


Debugging
---------

Run ``bitbake -c do_rootfs senic-os-dev`` to trigger a build.
Then you can check ``tmp-glibc/work/senic_hub_beta-oe-linux-gnueabi/senic-os-dev/1.0-r0/rootfs/`` and inspect what the filesystem looks like.
In many cases this is enough and you can save yourself the trouble of writing the image to disk and physically boot into it.
