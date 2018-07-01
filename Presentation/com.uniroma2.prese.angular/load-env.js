/*
  ts-node load-env &&
  Requirements:
  # typescript in node
  npm install -g ts-node
  # Install a TypeScript compiler.
  npm install -g typescript
*/

const fs           = require('fs');
const chalk        = require("chalk");
const environments = ['dev','prod'];
const filepath     = './src/environments/ALL-ENVIRONMENTS.ts';
let result         = {};

console.log(chalk.cyan.bold('Importing all environments...'));
console.log(chalk.cyan.bold('============================='));

// elimina file precedente se esiste
if (fs.existsSync(filepath)) {
  fs.unlinkSync(filepath);
}

environments.forEach(function (element) {
  let e = require(`./src/environments/environment.${element}.ts`);
  result[element] = e.environment;
}, this);
let objstring = JSON.stringify(result)
let file = `export const environments = ${objstring};`;

// write file to be inported as all environments
fs.writeFile('./src/environments/ALL-ENVIRONMENTS.ts', file, function (err) {
  if (err) {
    console.log(chalk.red.bold('Error saving file: '), err);
  } else {
    console.log(chalk.green.bold(`File ./src/environments/allenvironments.ts has been saved properly.\n`), result);
  }
});
