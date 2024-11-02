/** @type {import('tailwindcss').Config} */

module.exports = {
  content: [
      "./**/*.{jsx,tsx}",
      "./index.html"
  ],
  theme: {
    extend: {
        fontFamily: {
            sans: ['Fira Sans', 'sans-serif'],
        },
        colors: {
            red: "#dc2626",
            grey: "#94a3b8",
            blue: "#3b82f6",
            green: "#65a30d"
        }
    },
  },
  plugins: []
}

