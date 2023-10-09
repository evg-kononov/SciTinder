Запускаем три экземпляра приложения fast-api на портах 3002, 3012 и 3022 с использованием docker compose.

Проводим нагрузочное тестирование с использованием Apache benchmark:
ab -k -c 1 -n 1000 http://localhost:80/counterer/.

### Три экземпляра приложения
Round Robin:
- Concurrency Level:      1
- Time taken for tests:   14.919 seconds
- Complete requests:      1000
- Failed requests:        0
- Keep-Alive requests:    999
- Total transferred:      155995 bytes
- HTML transferred:       3000 bytes
- Requests per second:    67.03 [#/sec] (mean)
- Time per request:       14.919 [ms] (mean)
- Time per request:       14.919 [ms] (mean, across all concurrent requests)
- Transfer rate:          10.21 [Kbytes/sec] received


Least Connection:
- Concurrency Level:      1
- Time taken for tests:   15.847 seconds
- Complete requests:      1000
- Failed requests:        7
- Keep-Alive requests:    999
- Total transferred:      156002 bytes
- HTML transferred:       3007 bytes
- Requests per second:    63.11 [#/sec] (mean)
- Time per request:       15.847 [ms] (mean)
- Time per request:       15.847 [ms] (mean, across all concurrent requests)
- Transfer rate:          9.61 [Kbytes/sec] received


### Два экземпляра приложения
Round Robin:
- Concurrency Level:      1
- Time taken for tests:   20.537 seconds
- Complete requests:      1000
- Failed requests:        0
- Keep-Alive requests:    999
- Total transferred:      156995 bytes
- HTML transferred:       4000 bytes
- Requests per second:    48.69 [#/sec] (mean)
- Time per request:       20.537 [ms] (mean)
- Time per request:       20.537 [ms] (mean, across all concurrent requests)
- Transfer rate:          7.47 [Kbytes/sec] received


Least Connection:
- Concurrency Level:      1
- Time taken for tests:   21.211 seconds
- Complete requests:      1000
- Failed requests:        0
- Keep-Alive requests:    999
- Total transferred:      156995 bytes
- HTML transferred:       4000 bytes
- Requests per second:    47.14 [#/sec] (mean)
- Time per request:       21.211 [ms] (mean)
- Time per request:       21.211 [ms] (mean, across all concurrent requests)
- Transfer rate:          7.23 [Kbytes/sec] received

### Один экземпляр приложения
Round Robin:
- Сoncurrency Level:      1
- Time taken for tests:   27.546 seconds
- Complete requests:      1000
- Failed requests:        0
- Keep-Alive requests:    999
- Total transferred:      156995 bytes
- HTML transferred:       4000 bytes
- Requests per second:    36.30 [#/sec] (mean)
- Time per request:       27.546 [ms] (mean)
- Time per request:       27.546 [ms] (mean, across all concurrent requests)
- Transfer rate:          5.57 [Kbytes/sec] received


Least Connection:
- Concurrency Level:      1
- Time taken for tests:   27.686 seconds
- Complete requests:      1000
- Failed requests:        0
- Keep-Alive requests:    999
- Total transferred:      156995 bytes
- HTML transferred:       4000 bytes
- Requests per second:    36.12 [#/sec] (mean)
- Time per request:       27.686 [ms] (mean)
- Time per request:       27.686 [ms] (mean, across all concurrent requests)
- Transfer rate:          5.54 [Kbytes/sec] received




