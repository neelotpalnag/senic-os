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


Debugging
---------

Run ``bitbake -c do_rootfs senic-os-dev`` to trigger a build.
Then you can check ``tmp-glibc/work/senic_hub_beta-oe-linux-gnueabi/senic-os-dev/1.0-r0/rootfs/`` and inspect what the filesystem looks like.
In many cases this is enough and you can save yourself the trouble of writing the image to disk and physically boot into it.


Configuring the wifi network manually
-------------------------------------

Normally, a freshly booted instance will come up in onboarding mode, meaning you can connect to and configure it via the app.

However, you can also connect via a serial console (i.e. ``screen /dev/tty.usbserial 115200``), login as root (without password) and connect to a wireless network like so::


-    nmcli dev wifi con <SSID> password <PASSWORD> ifname wlan0
