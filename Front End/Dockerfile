# Step 1: Build React app
FROM node:18-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# Step 2: Serve React app with Nginx
FROM nginx:alpine

# Copy build files to nginx html directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose frontend port
EXPOSE 3000

# Run Nginx
CMD ["nginx", "-g", "daemon off;"]
