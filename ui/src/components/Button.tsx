import React from "react";

interface ButtonProps {
  label: string
  color: string,
  onClick?: () => Promise<void>
}

const Button: React.FC<ButtonProps> = ({label, color, onClick}) => (
    <div role="button"
         onClick={onClick}
         className={color + " border border-gray-600 px-2 py-1 m-0.5 text-center text-white rounded"}>
      {label}
    </div>
);

export default Button
