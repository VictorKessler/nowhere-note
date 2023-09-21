#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE SCHEMA nowhere_note;
    ALTER USER "$POSTGRES_USER" SET search_path TO 'nowhere_note';
EOSQL