obj-m := senic-button.o
SRC := $(shell pwd)

all:	
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKEARCH) -C $(KERNEL_SRC) M=$(SRC) clean
