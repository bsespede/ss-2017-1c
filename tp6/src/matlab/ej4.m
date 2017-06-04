function ej4() 

  [speed time] = textread("../../results/4/total-evacuations.dat", "%f %f");
  
  %resultSpeed average minTime maxTime
  results = zeros(10, 4);
  for current = 10:10:size(time)
    results(current/10, 1) = speed(current, 1);
    results(current/10, 2) = mean(time(current-9:current, 1));
    results(current/10, 3) = min(time(current-9:current, 1));
    results(current/10, 4) = max(time(current-9:current, 1));
  endfor  
  
  errorbar(results(:, 1), results(:, 2), results(:, 4) - results(:, 3), ".");
  title("Tiempo de evacuacion total en funcion de velocidad deseada");
  xlabel("Velocidad deseada (m/s)");
  ylabel("Tiempo de evacuacion total (s)");
  print("../../plots/ej4.png", "-dpng");
  
endfunction