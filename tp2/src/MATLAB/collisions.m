function collisions()

 L1 = importdata('collisions30.txt');
 L2 = importdata('collisions50.txt');
 L3 = importdata('collisions70.txt');
 
 hold on;
 plot(L1(:,1),L1(:,2));
 plot(L2(:,1),L2(:,2));
 plot(L3(:,1),L3(:,2));
 
 legend('L = 30', 'L = 50', 'L = 70');
 title('Colisiones a lo largo del tiempo')
 xlabel('Paso');
 ylabel('Cantidad de colisiones');
 
 hold off;
end