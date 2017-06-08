
/*
 * Kernel Driver to handle interrupts of Button on Senic Hub
 *
 * NOTES: System Restart is implemented by binding GPIOA6 to 
 * <KEY_RESTART> in the device tree. This module should be implemented
 * in future to do hard-reset, config-reset and soft-reset based on the
 * UX requirement. Therefore this module is currently excluded from the
 * senic-os image.
 *
 * Author:
 * 	Aravinth Panch <aravinth@senic.com>
 *
 * This software is licensed under the terms of the GNU General Public
 * License version 2, as published by the Free Software Foundation, and
 * may be copied, distributed, and modified under those terms.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 */

#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/gpio.h>
#include <linux/interrupt.h>


/* 
* Define GPIO for Button at PG7  
* LINUX GPIO NUMBER = (GPIO BANK - 1) * 32 + GPIO BIT
* 199 = (G - 1) * 32 + 7
* 199 = (7 - 1) * 32 + 7
*/
static struct gpio button = {
	199, //PG7 
	GPIOF_IN,
	"BUTTON_1"
};

/*
 * Module init function
 */
static int __init senic_button_init(void)
{
	int ret = 0;
	printk(KERN_INFO "%s\n", __func__);

	// Register the Button
	ret = gpio_request(button.gpio, button.label);

	// Exit if resource busy
	if (ret) {
		printk(KERN_ERR "Unable to request Senic Button: %d\n", ret);
		return ENXIO;
	}

	// Read the Button value
	printk(KERN_INFO "Senic Button : %d\n", gpio_get_value(button.gpio));
	return 0;
}

/**
 * Module exit function
 */
static void __exit senic_button_exit(void)
{
	printk(KERN_INFO "%s\n", __func__);

	// Unregister the button
	gpio_free(button.gpio);
}


MODULE_LICENSE("GPL");
MODULE_AUTHOR("Aravinth Panch <aravinth@senic.com>");
MODULE_DESCRIPTION("Kernel Driver to handle interrupts of Button on Senic Hub");

module_init(senic_button_init);
module_exit(senic_button_exit);
