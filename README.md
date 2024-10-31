# pam-styrk-yrkeskategori-mapper
Bibliotek for mapping fra Styrk-koder til PAM-kategori-koder og omvendt.

Basert på Styrk til yrkeskategori-mapping v0.

## Endre styrk kategorisering

All styrk mapping ligger i [styrk_category_mapping.csv](src/main/resources/styrk_category_mapping.csv)

Når du gjør endringer der og har pushet til master, så vil en ny tag bli laget eks. "1.20241030-dc26b440".
Du kan finne nyeste tag her: https://github.com/navikt/pam-styrk-yrkeskategori-mapper/tags

Når du har denne så trenger du å oppdatere alle apper som har koblet seg til styrk mappingen med den nye versjonen.
Her er de fleste (mulig det er mer)
* [pam-stilling-opensearch-indexer](https://github.com/navikt/pam-stilling-opensearch-indexer) (merk at du trenger å reindeksere)
* [pam-ad](https://github.com/navikt/pam-ad)
* [pam-stilling-feed](https://github.com/navikt/pam-stilling-feed)
* [pam-import-api](https://github.com/navikt/pam-import-api)
* [pam-ad-rapids](https://github.com/navikt/pam-ad-rapids) (merk at det er TRE forskjellige apper her)
* [pam-aareg-oppslag](https://github.com/navikt/pam-aareg-oppslag)