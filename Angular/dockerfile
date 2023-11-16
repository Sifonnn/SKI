# Utilisez une image Node.js comme base
FROM node:14

# Définissez le répertoire de travail dans le conteneur
WORKDIR /app

# Copiez les fichiers package.json et package-lock.json dans le conteneur
COPY package*.json ./

# Installez les dépendances du projet
RUN npm install

# Copiez le reste des fichiers de votre projet dans le conteneur
COPY . .

# Compilez votre projet Angular
RUN npm run build

# Exposez le port sur lequel l'application Angular s'exécute (par défaut 4200)
EXPOSE 4200

# Démarrez votre application Angular
CMD ["npm", "start"]
