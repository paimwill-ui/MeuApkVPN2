package com.minhavpn.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
  private LinearLayout layout;
  private TextView status;
  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    layout = new LinearLayout(this); layout.setOrientation(LinearLayout.VERTICAL);
    layout.setPadding(48,64,48,32); layout.setBackgroundColor(Color.rgb(246,248,252));
    TextView title = new TextView(this); title.setText("Minha VPN"); title.setTextSize(30); title.setTextColor(Color.rgb(18,48,86)); title.setGravity(Gravity.CENTER); layout.addView(title);
    status = new TextView(this); status.setText("Nenhuma conexão gerenciada neste aplicativo.\nUse o WireGuard oficial para ativar sua VPN."); status.setTextSize(17); status.setPadding(0,45,0,30); layout.addView(status);
    Button install = button("Instalar / abrir WireGuard");
    install.setOnClickListener(v -> { Intent i = getPackageManager().getLaunchIntentForPackage("com.wireguard.android"); if(i != null) startActivity(i); else { Intent store = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.wireguard.android")); startActivity(store); } });
    Button help = button("Como configurar minha VPN");
    help.setOnClickListener(v -> status.setText("1. Prepare seu servidor WireGuard na nuvem.\n2. Gere um perfil de cliente .conf.\n3. Abra o WireGuard oficial.\n4. Importe o arquivo ou QR code.\n5. Ative o túnel no WireGuard.\n\nEste app não cria nem ativa túneis sozinho."));
    setContentView(layout);
  }
  private Button button(String label) { Button b = new Button(this); b.setText(label); layout.addView(b); return b; }
}
