Goal is to find an embedded data store that is very easy to use in Java. It should be  
reliable, durable and relatively high write volume with concurrent reads and writes. 
The intent is to use this as a collection layer to receive metrics, logs and other time
series data from EC2/GCE and other cloud VMs. It should not degrade significantly in 
write volume with 100GB of file store.

Tests results are from Mac Pro machine with no special disk upgrades

Looks like H2/MVStore is the winner with the caveat that it takes a lot more disk space (> 10x).
6.1M entries in 5 minutes.


--- MapDB ---

Items: 7527
Time: 60 s
Items per second: 125

Items: 15389
Time: 120 s
Items per second: 128

Items: 22529
Time: 180 s
Items per second: 125

Items: 29484
Time: 240 s
Items per second: 122

Items: 36203
Time: 300 s
Items per second: 120

Items: 42590
Time: 360 s
Items per second: 118

Items: 48640
Time: 420 s
Items per second: 115

Items: 54392
Time: 480 s
Items per second: 113

Items: 59843
Time: 540 s
Items per second: 110

Items: 65122
Time: 600 s
Items per second: 108

Items: 70409
Time: 660 s
Items per second: 106

Items: 75503
Time: 720 s
Items per second: 104

Items: 80409
Time: 780 s
Items per second: 103

Items: 85056
Time: 840 s
Items per second: 101

Items: 89556
Time: 900 s
Items per second: 99

File /Users/rogerc/embedded-db-testing/databases/MapDB-test.db, total file size: 1 MBs
Average entry size: 11 bytes


--- H2/MVStore ---

Items: 1194139
Time: 60 s
Items per second: 19902

Items: 2442463
Time: 120 s
Items per second: 20353

Items: 3687543
Time: 180 s
Items per second: 20481

Items: 4926232
Time: 240 s
Items per second: 20524

Items: 6165065
Time: 300 s
Items per second: 20550

Items: 7403112
Time: 360 s
Items per second: 20563

Items: 8639824
Time: 420 s
Items per second: 20570

Items: 9871677
Time: 480 s
Items per second: 20565

Items: 11109879
Time: 540 s
Items per second: 20571

Items: 12345239
Time: 600 s
Items per second: 20573

Items: 13577785
Time: 660 s
Items per second: 20571

Items: 14815145
Time: 720 s
Items per second: 20575

Items: 16047252
Time: 780 s
Items per second: 20572

Items: 17280162
Time: 840 s
Items per second: 20571

Items: 18514285
Time: 900 s
Items per second: 20571

File /Users/rogerc/embedded-db-testing/databases/H2-test.db, total file size: 5668 MBs
Average entry size: 321 bytes

