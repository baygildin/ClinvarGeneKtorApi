# ClinvarGeneKtorApi

REST HTTP сервис на Kotlin/Ktor, который возвращает аннотацию генетического варианта из базы Clinvar.  
Работает с большим архивом, индексированным через tabix.

Сервис разработан в рамках тестового задания.  
Аннотация возвращается по запросу с параметрами `rac`, `lap`, `rap`, `refkey`.

---

## 🔧 Сборка и запуск

### 1. Собрать fat-jar

```bash
./gradlew clean fatJar
```
### 2. Собрать Docker-образ
```
docker build -t clinvar-api .
```
### 3.Запустить контейнер, передав путь к архиву
```
docker run -p 8080:8080 -e FILE_PATH=/data/test_data.tsv.gz -v "C:\Users\S\Desktop\archive_stem\smallstem:/data" clinvar-api
```

FILE_PATH — переменная окружения с путём до .tsv.gz файла внутри контейнера.

-v монтирует локальную папку с архивом в контейнер (в контейнере доступна как /data).
##### 🐳 Пример запуска с другим архивом

Для своего архива используйте:
```
docker run -p 8080:8080 -e FILE_PATH=/data/your_file.tsv.gz -v "/path/to/folder:/data" clinvar-api
```
Где:
- `/path/to/folder` — путь к папке, содержащей `your_file.tsv.gz`.
- `FILE_PATH` — путь к файлу внутри контейнера.

Пример запроса
```
http://localhost:8080/info?rac=NC_000001.11&lap=925951&rap=925953&refkey=A
```
Пример успешного ответа
```
{
  "rac": "NC_000001.11",
  "lap": 925951,
  "rap": 925953,
  "refkey": "A",
  "id": "1019397",
  "significance": "Uncertain_significance",
  "criteria": "criteria_provided,_single_submitter",
  "variantType": "single_nucleotide_variant"
}
```