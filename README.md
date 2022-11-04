# Advent of Beers

## Contexte

Nous sommes début Novembre, la saison des fêtes de fin d'année débute et un sacré lot de calendriers de l'avent ont déjà
envahi nos magasins ! Mais saviez-vous qu'il existe également des calendriers de l'avent pour la bière ? Les amateurs de
chocolat ne sont pas les seuls à se régaler en dégustant un exemplaire par jour pendant 24 jours :wink:

## Règles métier

L'alcool est dangereux pour la santé, et à consommer avec modération : nous souhaitons que notre API n'accepte de
générer des calendriers de l'Avent que du 1er Novembre au 25 Décembre de l'année courante. En dehors de cette période
temporelle, il est impossible de générer un calendrier de l'Avent.

Notre application ne stockera aucun référentiel de bières, nous déléguerons cette tâche dantesque à
la [Punk API](https://punkapi.com/documentation/v2) qui nous permettra de choisir quelles bières ajouter à notre
calendrier.

Pour requêter cette API, nous allons
utiliser [3 paramètres de recherche](https://unepetitemousse.fr/blog/ibu-acronymes-de-la-biere/):

- l'ABV (pour **Alcohol By Volume**) définit la quantité d'alcool pure relative à la quantité de boisson ingérée,
  s'exprime en % d'alcool, c'est ce qui est lisible sur la bouteille et connu de tous !
- l'IBU (pour **International Bitterness Unit**) est une mesure permettant d’évaluer l’amertume de la boisson
- l'EBC (pour **European Brewery Convention**) est une échelle qui permet de classifier les bières en fonction de leur
  couleur

Pour générer un calendrier de l'Avent, l'utilisateur doit fournir trois variables :

- La teinte des bières souhaitées : claires, normales ou sombres. **Une bière est considérée comme claire lorsque son EBC est compris entre 1 et 10, normale entre 11 et 17, sombre
  au-delà de 17**

- L'amertume souhaitée : douce, normale ou amère. **Une bière est considérée comme douce si son IBU est inférieur à 15,
  normale entre 15 et 17 et amère au-delà de 17**

- La quantité d'alcool contenue acceptée : sans alcool, peu d'alcool, normal, beaucoup (_trop_) d'alcool. **Une bière
  est considérée comme sans alcool si elle a un ABV inférieur à 1, avec peu d'alcool entre 1 et 5,
  normale entre 5 et 10 et forte au-delà de 10**

Le calendrier de l'Avent sera renvoyée au client sous la forme d'un tableau associatif qui, pour chaque jour du 1er
Décembre au 24 **inclus**, proposera une bière aléatoire dans le référentiel disponible sur l'API mentionnée
précédemment !
