var express = require('express');
var path = require('path');
const { Eureka } = require('eureka-js-client');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
const port = 3000;

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

app.use('/', indexRouter);
app.use('/users', usersRouter);

module.exports = app;

const client = new Eureka({
    eureka: {
        host: 'localhost',
        port: 8761,
        servicePath: '/eureka/apps',
    },
    instance: {
        app: 'professeurService',
        instanceId: 'dbServer',
        hostName: 'localhost',
        ipAddr: '127.0.0.1',
        port: {
            '$': port,
            '@enabled': true
        },
        vipAddress: 'professeurService',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn'
        }
    },
});

client.start(error => {
    if (error) {
        console.error('Error registering with Eureka:', error);
    } else {
        console.log('Successfully registered with Eureka');
    }
});


let professeurs = [
    { id: 1, name: 'John Doe', subject: 'Mathematics' },
    { id: 2, name: 'Jane Smith', subject: 'History' },
];

app.post('/professeurs', (req, res) => {
    const { name, subject } = req.body;
    const newProfesseur = {
        id: professeurs.length + 1,
        name,
        subject,
    };
    professeurs.push(newProfesseur);
    res.status(201).json(newProfesseur);
});

app.get('/professeurs', (req, res) => {
    res.json(professeurs);
});

app.get('/professeurs/:id', (req, res) => {
    const professeur = professeurs.find(p => p.id === parseInt(req.params.id));
    if (!professeur) return res.status(404).send('Professeur not found');
    res.json(professeur);
});

app.put('/professeurs/:id', (req, res) => {
    const professeur = professeurs.find(p => p.id === parseInt(req.params.id));
    if (!professeur) return res.status(404).send('Professeur not found');

    const { name, subject } = req.body;
    professeur.name = name || professeur.name;
    professeur.subject = subject || professeur.subject;

    res.json(professeur);
});

app.delete('/professeurs/:id', (req, res) => {
    const index = professeurs.findIndex(p => p.id === parseInt(req.params.id));
    if (index === -1) return res.status(404).send('Professeur not found');

    professeurs.splice(index, 1);
    res.status(204).send();
});

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
});

