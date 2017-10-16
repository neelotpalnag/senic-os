We have a custom version of the bluez recipe (as oppsed to simply ``.bbappending`` it for two reasons:

- we need to customize ``bluetooth.conf``

- we need a newer version than provided in morty (we need >= 5.44 in order to support BLE advertising mode)

Ideally this would be achieved by clever path manipulation but for now we are working with a custom ``.bb`` file for the 5.44 version that inherits the upstream ``.inc``.

However, since we also modify ``bluetooth.conf`` we cannot 'piggy back' on the upstream files, so we keep a static copy of those around. These might go out of sync and should be checked periodically or every time this recipe breaks.
