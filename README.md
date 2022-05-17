# Project-MasterproefTool
This project consist of two different parts. The front-end that renders the react-webapp and the backend that handles the connection to the database.\
To run the project, you need to run both parts. Below you can find a details explanation

# Front-end 

To run the front-end, you need to open the project and type  `npm start` in the terminal.\
This will run the app in development mode.

Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any errors in the console.

# Back-end

To run the backend, you simply need to open the project in an IDE and run the file `MasterproefToolApplication`.\
This will open the project in development mode.\
The back-end will run on [http://localhost:8080](http://localhost:8080)

# HTTPS Configuration

To configure the HTTPS-connection, you simply have to follow 4 steps. First off, install the mkcert package globally.
Then, you have to create the required Certificate Authorities and Certificates. Then, you have to install the created Certificate Authrity as the Trusted root certificate Authority.
Last, update the start script with https and certificate flags. For more in-depth information [https://medium.com/@praveenmobdev/localhost-as-https-with-reactjs-app-on-windows-a1270d7fbd1f](https://medium.com/@praveenmobdev/localhost-as-https-with-reactjs-app-on-windows-a1270d7fbd1f)

# Contributors
- Lucas Van Der Stuyft
- Siemen Van der Hoeven
- Daan Versavel
