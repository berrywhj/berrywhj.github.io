// Create a new Date object for the current time
const now = new Date();

// Function to format the timezone offset in +hhmm or -hhmm format
function formatTimezoneOffset(date) {
  const offset = -date.getTimezoneOffset();
  const sign = offset >= 0 ? '+' : '-';
  const pad = (num) => (num < 10 ? '0' : '') + num;
  const hours = pad(Math.floor(Math.abs(offset) / 60));
  const minutes = pad(Math.abs(offset) % 60);
  return `${sign}${hours}${minutes}`;
}

// Format the date and time in the Jekyll post date format
const jekyllPostDateFormat = `${now.getFullYear()}-${(now.getMonth() + 1).toString().padStart(2, '0')}-${now.getDate().toString().padStart(2, '0')} ${now.getHours().toString().padStart(2, '0')}:${now.getMinutes().toString().padStart(2, '0')}:${now.getSeconds().toString().padStart(2, '0')} ${formatTimezoneOffset(now)}`;

console.log(jekyllPostDateFormat);

// For OS X with node.js:

function pbcopy(data) {
    var proc = require('child_process').spawn('pbcopy'); 
    proc.stdin.write(data); proc.stdin.end();
}
pbcopy(jekyllPostDateFormat)