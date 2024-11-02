import React from "react";

interface ButtonProps {
  type: 'button' | 'reset' | 'submit'
  label: string
  color: string,
  onClick?: () => Promise<void>
}

const Button: React.FC<ButtonProps> = ({type, label, color, onClick}) => (
    <button type={type}
         onClick={onClick}
         className={color + " w-full border border-gray-600 px-2 py-1 m-0.5 text-center text-white rounded select-none hover:shadow-lg"}>
      {label}
    </button>
);

export default Button
