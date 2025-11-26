# 📘 Sistema de Consultas Médicas em Java (Swing + MongoDB)
 
Disciplina: Banco de Dados
Professor: Howard Roatti
Instituição: FAESA
Última atualização: 26/11/2025
Vídeo de apresentação: https://youtu.be/_czu8RoQdUs

Integrantes: Estevão Matos de Souza, Gabriel Araujo Ceruti Castro, Gabriel Araujo Valoni,
Heitor Lopes Trindade Fadlalah, Renan Fortunato Silveira, Thiago Rosetti Miranda
# Turma: 4HC1A

Este projeto apresenta um **sistema completo de Consultas Médicas**, desenvolvido em **Java**, utilizando:

- Interface gráfica em **Swing**
- Persistência de dados em **MongoDB**
- Arquitetura organizada em camadas:
  `model/`, `dao/`, `config/`, `ui/`
- CRUD completo: **Cadastrar, Listar, Editar e Excluir Consultas**

O sistema manipula documentos MongoDB representando **consultas médicas**, contendo dados como paciente, médico, data, especialidade e status.

---

# 🚀 Como executar o sistema

Antes de executar o sistema, é necessário:

### ✔ Ter o **MongoDB rodando**

Local ou Atlas (nuvem).

### ✔ Ter o **Java 17+** instalado

(ou configurar o VS Code para compilar com este JDK)

### ✔ Ter o driver do MongoDB adicionado ao classpath

Se estiver usando Maven, basta incluir no `pom.xml`.

---

# 🗄 1. Configuração da Conexão com o MongoDB

A classe responsável pela conexão é:

```
src/config/MongoConfig.java
```

Trecho principal:

```java
private static final String URI = "mongodb://localhost:27017";
private static final String DB_NAME = "clinica";

MongoClientSettings settings = MongoClientSettings.builder()
        .codecRegistry(registry)
        .applyConnectionString(new ConnectionString(URI))
        .build();
```

## ✔ Como ajustar a conexão

### 🔹 Para usar MongoDB local

```java
private static final String URI = "mongodb://localhost:27017";
```

### 🔹 Para usar MongoDB Atlas

```java
private static final String URI = "mongodb+srv://USUARIO:SENHA@cluster.mongodb.net/?retryWrites=true&w=majority";
```

### 🔹 Nome do banco de dados

```
clinica
```

### 🔹 Collection utilizada

```
consultas
```

---

# 🧱 2. Organização do Projeto

A estrutura do sistema é a seguinte:

```
src/
 ├── config/
 │     └── MongoConfig.java        # Conexão com o MongoDB
 │
 ├── dao/
 │     └── ConsultaDAO.java        # Métodos CRUD e comunicação com a collection
 │
 ├── model/
 │     └── Consulta.java           # Entidade Consulta (mapeada para MongoDB)
 │
 ├── ui/
 │     ├── MainUI.java             # Tela principal (listagem + botões)
 │     ├── FormConsulta.java       # Tela de cadastro / edição
 │     └── TableModelConsulta.java # Modelo da tabela Swing
 │
 └── Main.java (opcional)
```

---

# 🖥 3. Funcionamento do Sistema

## ✔ Tela Principal — `MainUI.java`

Permite:

- Listar consultas
- Inserir consulta
- Editar consulta
- Excluir consulta
- Atualizar a tabela

A interface utiliza um `JTable` com `TableModelConsulta`.

---

## ✔ Cadastro / Edição — `FormConsulta.java`

Campos disponíveis:

- Paciente
- Médico
- Especialidade
- Descrição
- Data (gerada automaticamente na criação)
- Consulta realizada (checkbox)

---

## ✔ Acesso ao MongoDB — `ConsultaDAO.java`

O DAO implementa:

- `salvar(Consulta c)`
- `listar()`
- `atualizar(Consulta c)`
- `excluir(ObjectId id)`

Operações seguem o padrão oficial do driver MongoDB.

---

# 💾 4. Modelo de Documento no MongoDB

```json
{
  "_id": ObjectId("..."),
  "paciente": "João da Silva",
  "medico": "Dra. Ana Souza",
  "especialidade": "Cardiologia",
  "dataHora": ISODate("2025-02-01T14:30:00Z"),
  "descricao": "Retorno de exames",
  "realizada": false
}
```

---

# 🏃 5. Como Executar

### ✔ Compilar (VS Code + Maven)

```sh
mvn clean package
```

Gerará:

```
target/consultas-medicas.jar
```

### ✔ Executar

```sh
java -jar target/consultas-medicas.jar
```

---

# 📦 6. Gerar .jar Manualmente (sem Maven)

### 1. Compilar classes

```sh
javac -cp "lib/*" -d bin src/**/*.java
```

### 2. Criar MANIFEST.MF

```
Main-Class: ui.MainUI
Class-Path: lib/mongodb-driver-sync.jar
```

### 3. Gerar JAR

```sh
jar cfm consultas-medicas.jar MANIFEST.MF -C bin .
```

### 4. Executar

```sh
java -jar consultas-medicas.jar
```

---

# 📚 Bibliotecas Utilizadas

- MongoDB Java Driver
- Java Swing
- JDK 17+

---

# 📞 Contato

Caso deseje uma versão:

- JavaFX
- NetBeans + .form
- API REST (Spring Boot)
- Docker + MongoDB
- Pacote ZIP completo

Basta solicitar!


