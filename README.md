Goal is to find an embedded data store that is very easy to use in Java. It should be  
reliable, durable and relatively high write volume with concurrent reads and writes. 
The intent is to use this as a collection layer to receive metrics, logs and other time
series data from EC2/GCE and other cloud VMs. It should not degrade significantly in 
write volume with 100GB of file store.

Tests results are from Mac Pro machine with no special disk upgrades

Looks like H2/MVStore is the winner with the caveat that it takes a lot more disk space (> 10x).
6.5M entries in 5 minutes.


--- MapDB ---

Items: 7434
Time: 60 s
Items per second: 123

Items: 15364
Time: 120 s
Items per second: 127

Items: 22762
Time: 180 s
Items per second: 126

Items: 29407
Time: 240 s
Items per second: 122

Items: 36186
Time: 300 s
Items per second: 120

File /Users/rogerc/embedded-db-testing/databases/MapDB-test.db, total file size: 1 MBs
Average entry size: 28 bytes


--- H2/MVStore ---

Items: 1222217
Time: 60 s
Items per second: 20000

Items: 2545467
Time: 120 s
Items per second: 21000

Items: 3864985
Time: 180 s
Items per second: 21000

Items: 5185749
Time: 240 s
Items per second: 21000

Items: 6500824
Time: 300 s
Items per second: 21000

File /Users/rogerc/embedded-db-testing/databases/H2-test.db, total file size: 1973 MBs
Average entry size: 318 bytes

