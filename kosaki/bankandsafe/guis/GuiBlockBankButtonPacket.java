/*
package kosaki.bankandsafe.guis;


public class GuiBlockBankButtonPacket
{
	/*
	 *今回パケットで扱うデータ
	 *//*
	private byte buttonId;

	public GuiBlockBankButtonPacket()
	{
	}

	/*
	 * パケットの生成には通常こちらを用いる.
	 *//*
	public GuiBlockBankButtonPacket(int _buttonId)
	{
		this.buttonId = (byte)_buttonId;
	}

	/*
	 * パケットのバイト列からデータを読みとる.
	 *//*
	@Override
	public void fromBytes(ByteBuff buffer)
	{
		buttonId = buffer.readByte();
	}

	/*
	 * パケットのバイト列へデータを書き込む.
	 *//*
	@Override
	public void toBytes(ByteBuff buffer)
	{
		buffer.writeByte(buttonId);
	}

	/*
	 * PacketHandlerでパケットのデータを参照するためのメソッド.
	 * 必要に応じてgetter, setter, 他を用意する.
	 *//*
	public byte getButtonId()
	{
		return buttonId;
	}
}
*/