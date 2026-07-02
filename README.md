# Country Explorer

A Java application that reads country, continent, ocean, and language
data from CSV files, builds a linked object model, and prints a
formatted profile for each country — including automatic hemisphere
classification derived from latitude and longitude.

## Features

- Parses CSV data (including quoted fields with embedded commas) for:
  - **Countries** — name, land area, coast length, population, coordinates
  - **Continents** — name, area
  - **Oceans** — name, area
  - **Languages** — name, number of speakers
- Resolves associations between a country and its continent, oceans,
  and languages by matching names across the loaded data
- Automatically classifies each country into two hemispheres based on
  its coordinates:
  - Latitude ≥ 0 → **Northern**, otherwise **Southern**
  - Longitude ≥ 0 → **Eastern**, otherwise **Western**
- Produces a formatted, human-readable summary for every country

## Project structure

```
src/
├── WorldMap/
│   ├── Continent.java     # continent name + area
│   ├── Country.java       # main entity; ties everything together
│   ├── Ocean.java         # ocean name + area
│   └── Language.java      # language name + speaker count
└── Hemisphere/
    ├── Hemisphere.java            # base classification class
    ├── HemisphereByLatitude.java  # Northern / Southern
    ├── HemisphereByLongitude.java # Eastern / Western
    └── RunHemisphere.java         # application entry point

Data/
├── continent.csv
├── ocean.csv
├── language.csv
└── country.csv
```

## Design notes

- Classes are split by responsibility across two packages: `WorldMap`
  (geographic entities) and `Hemisphere` (coordinate classification).
- `Hemisphere` is a small class hierarchy — `HemisphereByLatitude` and
  `HemisphereByLongitude` both extend a common `Hemisphere` base class,
  demonstrating inheritance and specialization.
- Every mutator that accepts a numeric value validates its input and
  throws an exception on invalid data (e.g. negative area, out-of-range
  coordinates), rather than silently accepting bad values.
- `Country` derives its hemisphere classification automatically the
  moment latitude/longitude are set — callers don't need to construct
  `Hemisphere` objects themselves.

## CSV format

Each CSV file's first row is a header row. Example (`country.csv`):

```
Name,LandArea,Continent,Coast,Ocean,Language,Population,Latitude,Longitude
Argentina,2780400,South America,8397,Atlantic,Spanish,44938712,-38.42,-63.62
Canada,9984670,North America,265523,"Atlantic, Pacific, Arctic","English, French",36991981,56.13,-106.35
```

## Building and running

Compile from the project root (this preserves the package structure):

```bash
javac -d out src/WorldMap/*.java src/Hemisphere/*.java
```

Run (the program expects the `Data/` folder relative to the working
directory):

```bash
java -cp out Hemisphere.RunHemisphere
```

## Example output

```
Country: Argentina:
  - Area: 2,780,400 km2
  - Continent: South America - Area: 17,461,112 km2
  - Coast: 8,397 km
  - Ocean(s): Atlantic (Area: 85,133,000 km2)
  - Language(s): Spanish (Speakers: 500,000,000)
  - Population: 44,938,712
  - Latitude: -38.42 (Southern)
  - Longitude: -63.62 (Western)
----------------------------
```

## Notes

This started as a two-part exercise (an initial pass that only parsed
and printed basic country data, followed by adding hemisphere
classification). The current version merges both into a single,
complete application.
