Запускаем три экземпляра приложения fast-api на портах 3002, 3012 и 3022 с использованием docker compose.

Проводим нагрузочное тестирование с использованием Apache benchmark:
ab -k -c 1 -n 1000 http://localhost:80/counterer/.

Round Robin:
- Concurrency Level:      1
- Time taken for tests:   41.430 seconds
- Complete requests:      1000
- Failed requests:        0
- Non-2xx responses:      1000
- Keep-Alive requests:    999
- Total transferred:      182995 bytes
- HTML transferred:       22000 bytes
- Requests per second:    24.14 [#/sec] (mean)
- Time per request:       41.430 [ms] (mean)
- Time per request:       41.430 [ms] (mean, across all concurrent requests)
- Transfer rate:          4.31 [Kbytes/sec] received

Least Connection:
- Concurrency Level:      1
- Time taken for tests:   57.737 seconds
- Complete requests:      1000
- Failed requests:        0
- Non-2xx responses:      1000
- Keep-Alive requests:    999
- Total transferred:      182995 bytes
- HTML transferred:       22000 bytes
- Requests per second:    17.32 [#/sec] (mean)
- Time per request:       57.737 [ms] (mean)
- Time per request:       57.737 [ms] (mean, across all concurrent requests)
- Transfer rate:          3.10 [Kbytes/sec] received




