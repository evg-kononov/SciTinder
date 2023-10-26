### Создание таблицы с использованием партиционирования:
```
CREATE TABLE measurement (
    city_id         int not null,
    logdate         date not null,
    peaktemp        int,
    unitsales       int
) PARTITION BY RANGE (logdate);
```

### Вставка данных:
```
INSERT INTO public.measurement(
	city_id, logdate, peaktemp, unitsales)
	VALUES (1, '2006-03-16', 1, 1);
```

### Добавление партиций:
```
CREATE TABLE measurement_y2006m02 PARTITION OF measurement
    FOR VALUES FROM ('2006-02-01') TO ('2006-03-01');

CREATE TABLE measurement_y2006m03 PARTITION OF measurement
    FOR VALUES FROM ('2006-03-01') TO ('2006-04-01');
```

### Удаление партиций:
```
DROP TABLE measurement_y2006m02
DROP TABLE measurement_y2006m03
```

### Создание локальных индексов:
```
CREATE INDEX ON measurement_y2006m02 (logdate)
CREATE INDEX ON measurement_y2006m03 (logdate)
```

### Создание глобальных индексов:


### Выборка данных с использованием индексов:
```
SELECT * FROM measurement WHERE logdate >= DATE '2006-01-01';
SELECT * FROM measurement_y2006m02 WHERE logdate >= DATE '2006-01-01';
```
