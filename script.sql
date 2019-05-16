DROP TABLE profiles;
CREATE TABLE profiles
(
  profile_id          VARCHAR(255) NOT NULL
    CONSTRAINT profiles_pkey
    PRIMARY KEY,
  first_name       TEXT,
  last_name        TEXT,
  screen_name      TEXT,
  sex              TEXT,
  online           TEXT,
  birthday         TEXT,
  city             TEXT,
  country          TEXT,
  mobile_phone     TEXT,
  home_phone       TEXT,
  skype            TEXT,
  facebook         TEXT,
  twitter          TEXT,
  livejournal      TEXT,
  instagram        TEXT,
  status           TEXT,
  last_seen        TEXT,
  career           TEXT,
  military         TEXT,
  university       TEXT,
  home_town        TEXT,
  photo_link       TEXT,
  relation         TEXT,
  relation_partner TEXT,
  interests        TEXT,
  music            TEXT,
  activities       TEXT,
  movies           TEXT,
  tv               TEXT,
  books            TEXT,
  games            TEXT,
  schools          TEXT,
  about            TEXT,
  quotes           TEXT,
  political        TEXT,
  languages        TEXT,
  religion         TEXT,
  inspired_by      TEXT,
  people_main      TEXT,
  life_main        TEXT,
  smoking          TEXT,
  alcohol          TEXT,
  deactivated      TEXT
);

DROP TABLE products;
CREATE TABLE products
(
  product_id          VARCHAR(255) NOT NULL
    CONSTRAINT products_pkey
    PRIMARY KEY,
  name              TEXT
);
