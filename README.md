# COMEXPORT-API
API para teste prático.

## 1. Pré-requesitos
* [Java8+](https://www.java.com/download/)
* [Spring Tool Suite](https://spring.io/tools)
* [Docker](https://www.docker.com/)
* [Git](https://git-scm.com/downloads)

## 2. Download
    git clone https://github.com/craitz/comexport-api.git (HTTPS)
    git clone git@github.com:craitz/comexport-api.git (SSH)

## 3. Build
Importe o projeto no **STS** ou **Eclipse**. No menu principal, vá em **Run -> Run Configurations**. Escolha o diretório base como sendo o diretório raíz do seu projeto e definal _**clean package**_ em **Goals**.

![comexport-api build](https://github.com/craitz/comexport-api/blob/master/comexport-build.png)

O arquivo **comexport-0.0.1-SNAPSHOT.jar** vai ser gerado no diretório **/target**.

## 4. Criando e subindo os containers no Docker
No diretório raíz do projeto crie um arquivo chamado **Dockerfile**, com a seguinte configuração:

    FROM openjdk:8-jdk-alpine
    COPY ./target/comexport-0.0.1-SNAPSHOT.jar /usr/src/comexport/
    WORKDIR /usr/src/comexport/
    EXPOSE 8080
    CMD ["java", "-jar", "comexport-0.0.1-SNAPSHOT.jar"]

Para criar a imagem da API, ainda dentro do diretório raíz, execute o seguinte comando:

    docker build -t comexport-docker-build .

Confira se foi corretamente criado, utilizando o comando para listar as imagens:

    docker images

Como exemplo, para subir duas instâncias da API, utilize o seguinte comando duas vezes, alterando apenas a porta que será exposta:

    docker run -p 9000:8080 comexport-docker-build
    docker run -p 9001:8080 comexport-docker-build

## 5. Navegação
Com as instâncias rodando, podemos acessar a API utilizando as respectivas urls base:

    http://localhost:9000/
    http://localhost:9001/
