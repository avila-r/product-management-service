FROM postgres:16.1

ENV POSTGRES_DB commerce_db
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 123

COPY init-db.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]
