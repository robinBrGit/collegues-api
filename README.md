# collegues-api 
URL = https://robin-br-collegues-api.herokuapp.com/collegues

Data set :

-noms = ["Martin","Bernard","Thomas","Petit","Robert","Richard","Durand","Dubois","Moreau","Laurent"]

GET /collegues?nom=XXX recherche les collègues dont le nom est fourni.

GET /collegues/MATRICULE récupère les informations d'un collègue dont le matricule est fourni.

POST /collegues créer un nouveau collegue dans le système (ex)

```json
{
"nom" : "xxx",
"prenoms" : "xxxx",
"email" : "xxx.xxxx@xxx.xxx",
"dateDeNaissance" : "1900-01-01",
"photoUrl" : "https://randomuser.me/api/portraits/men/76.jpg"
}
```

PATCH /collegues/MATRICULE modifie un collegue.
parametre accepté :

```json
{
    "photoUrl" : "https://randomuser.me/api/portraits/men/76.jpg",
    "email" : "email@ef.fr"
}
```
